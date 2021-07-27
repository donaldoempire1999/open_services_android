package com.example.openservices.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.adapters.PublicationAdapter;
import com.example.openservices.adapters.UserAdapter;
import com.example.openservices.databinding.FragmentSearchResultsBinding;
import com.example.openservices.models.Publication;
import com.example.openservices.models.User;
import com.example.openservices.ui.persondetail.PersonDetailFragment;
import com.example.openservices.ui.publicationdetail.PublicationDetailsFragment;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.viewmodels.PublicationViewModel;
import com.example.openservices.viewmodels.UserViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SearchResultsFragment extends Fragment {

    private FragmentSearchResultsBinding dataBiding;
    private PublicationViewModel publicationViewModel;
    private UserViewModel userViewModel;

    private FragmentActivity activity;
    private Context context;

    private PublicationAdapter publicationAdapter;
    private UserAdapter userAdapter;
    private ArrayAdapter<String> autocompleteAdapter;

    //Data
    private ArrayList<Publication> mPublications;
    private ArrayList<User> mUsers;
    private ArrayList<String> autoCompleteValues;

    private String searchType = ConstantValue.SEARCH_TYPE_POSTS;
    private String searchMatching = ConstantValue.SEARCH_COLLECTION_KEY_WORD_FACETED;
    private String searchKeyWord = "";
    private String searchCollection = ConstantValue.SEARCH_COLLECTION_PUBLICATIONS;
    private boolean isProvider = true;

    public SearchResultsFragment() {
    }

    public static SearchResultsFragment newInstance(String searchKeyWord, String searchType, String searchMatching, String searchCollection, boolean isProvider) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putString(ConstantValue.ARG_PARAM_SEARCH_MATCHING, searchMatching);
        args.putString(ConstantValue.ARG_PARAM_SEARCH_KEYWORD, searchKeyWord);
        args.putString(ConstantValue.ARG_PARAM_SEARCH_TYPE, searchType);
        args.putString(ConstantValue.ARG_PARAM_SEARCH_COLLECTION, searchCollection);
        args.putBoolean(ConstantValue.ARG_PARAM_SEARCH_IS_PROVIDER, isProvider);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchMatching = getArguments().getString(ConstantValue.ARG_PARAM_SEARCH_MATCHING);
            searchKeyWord = getArguments().getString(ConstantValue.ARG_PARAM_SEARCH_KEYWORD);
            searchType = getArguments().getString(ConstantValue.ARG_PARAM_SEARCH_TYPE);
            searchCollection = getArguments().getString(ConstantValue.ARG_PARAM_SEARCH_COLLECTION);
            isProvider = getArguments().getBoolean(ConstantValue.ARG_PARAM_SEARCH_IS_PROVIDER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search_results, container, false);
        View view = dataBiding.getRoot();
        context = getContext();
        activity = getActivity();

        if (activity != null) {
            doInitializations();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupAutoCompleteAdapters();
        setupAdapters();
        checkInteractions();
    }

    private void setupAutoCompleteAdapters() {
        //Autocomplete adapter
        autocompleteAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, autoCompleteValues);
        autocompleteAdapter.notifyDataSetChanged();
        dataBiding.autoCompleteTextView.setAdapter(autocompleteAdapter);
    }

    private void checkInteractions() {
        dataBiding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchKeyWord = autoCompleteValues.get(position);
                loadPostsUsers(1);
            }
        });
        dataBiding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        dataBiding.autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                autoCompleteValues.clear();
                if (!s.toString().isEmpty()){
                    searchKeyWord = s.toString();
                    loadPostsUsers(0);
                }else{
                    searchKeyWord = "";
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        dataBiding.imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPostsUsers(1);
            }
        });
    }

    private void loadPostsUsers(int loadType) {
        if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)){
            if (isProvider){
                searchProvidersUsers(loadType);
            }else{
                searchRequestersUsers(loadType);
            }
        }else{
            searchPublications(loadType);
        }
    }

    private void searchRequestersUsers(int loadType) {
        if (loadType <= 0){
            dataBiding.setIsLoadingAutoComplete(true);
        }else{
            mPublications.clear();
            mUsers.clear();
            dataBiding.setIsLoadingMore(true);
        }
        userViewModel.searchUsers(searchMatching, searchCollection, searchKeyWord).observe(activity, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (loadType <= 0){
                    if (users != null) {
                        if (users.size() > 0) {
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
                                for (User user : users){
                                    autoCompleteValues.add(user.getCv().getMain_activity());
                                }
                                loadMoreSuggestions();
                            }else {
                                dataBiding.setIsLoadingAutoComplete(false);
                            }
                        }else{
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS))
                                userAdapter.notifyDataSetChanged();
                            dataBiding.setIsLoadingAutoComplete(false);
                        }
                    }else{
                        dataBiding.setIsLoadingAutoComplete(false);
                    }
                }else{
                    if (users != null) {
                        if (users.size() > 0) {
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
                                for (User user : users){
                                    mUsers.add(user);
                                }
                                loadMoreUsers();
                            }else {
                                mUsers.clear();
                                dataBiding.setIsLoadingMore(false);
                            }
                        }else{
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS))
                                userAdapter.notifyDataSetChanged();
                            dataBiding.setIsLoadingMore(false);
                        }
                    }else{
                        dataBiding.setIsLoadingMore(false);
                    }
                }
            }
        });
    }

    private void searchProvidersUsers(int loadType) {
        if (loadType <= 0){
            dataBiding.setIsLoadingAutoComplete(true);
        }else{
            mPublications.clear();
            mUsers.clear();
            dataBiding.setIsLoadingMore(true);
        }
        userViewModel.searchUsers(searchMatching, searchCollection, searchKeyWord).observe(activity, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (loadType <= 0){
                    if (users != null) {
                        if (users.size() > 0) {
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
                                for (User user : users){
                                    autoCompleteValues.add(user.getCv().getMain_activity());
                                }
                                loadMoreSuggestions();
                            }else {
                                dataBiding.setIsLoadingAutoComplete(false);
                            }
                        }else{
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS))
                                userAdapter.notifyDataSetChanged();
                            dataBiding.setIsLoadingAutoComplete(false);
                        }
                    }else{
                        dataBiding.setIsLoadingAutoComplete(false);
                    }
                }else{
                    if (users != null) {
                        if (users.size() > 0) {
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
                                for (User user : users){
                                    mUsers.add(user);
                                }
                                loadMoreUsers();
                            }else {
                                mUsers.clear();
                                dataBiding.setIsLoadingMore(false);
                            }
                        }else{
                            if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS))
                                userAdapter.notifyDataSetChanged();
                            dataBiding.setIsLoadingMore(false);
                        }
                    }else{
                        dataBiding.setIsLoadingMore(false);
                    }
                }
            }
        });
    }

    private void loadMoreUsers() {
        userAdapter.notifyDataSetChanged();
        dataBiding.setIsLoadingMore(false);
    }

    private void searchPublications(int loadType) {
        if (loadType <= 0){
            dataBiding.setIsLoadingAutoComplete(true);
        }else{
            mUsers.clear();
            mPublications.clear();
            dataBiding.setIsLoadingMore(true);
        }
        publicationViewModel.searchPublications(searchMatching, searchCollection, searchKeyWord).observe(activity, new Observer<ArrayList<Publication>>() {
            @Override
            public void onChanged(ArrayList<Publication> publications) {
                if (loadType <= 0){
                    dataBiding.setIsLoadingAutoComplete(true);
                    if (publications != null) {
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)) {
                            for (Publication publication : publications){
                                autoCompleteValues.add(publication.getTask_description().getTitle());
                            }
                            loadMoreSuggestions();
                        }else{
                            dataBiding.setIsLoadingAutoComplete(false);
                        }
                    }else{
                        dataBiding.setIsLoadingAutoComplete(false);
                    }
                }else{
                    if (publications != null) {
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)) {
                            mPublications.addAll(publications);
                            loadMorePublications();
                        }else{
                            mPublications.clear();
                            dataBiding.setIsLoadingMore(false);
                        }
                    }else{
                        dataBiding.setIsLoadingMore(false);
                    }
                }
            }
        });
    }

    private void loadMoreSuggestions() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                autocompleteAdapter.notifyDataSetChanged();
                dataBiding.setIsLoadingAutoComplete(false);
            }
        });
    }

    private void loadMorePublications() {
        publicationAdapter.notifyDataSetChanged();
        dataBiding.setIsLoadingMore(false);
    }

    private void setupAdapters() {
        //Users and Posts adapters
        if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
            userAdapter = new UserAdapter(context, mUsers, new UserAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    goToUserDetails(position);
                }

                @Override
                public void onShareClick(int position) {
                    shareUser(position);
                }

                @Override
                public void onUserClick(int position) {
                    goToUserDetails(position);
                }
            });
            dataBiding.recyclerSearchResult.setAdapter(userAdapter);
        }else{
            publicationAdapter = new PublicationAdapter(context, mPublications, new PublicationAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    goToPostDetailsPage(position);
                }

                @Override
                public void onShareClick(int position) {
                    sharePublication(position);
                }

                @Override
                public void onLikeClick(int position) {
                    likePublication(position);
                }

                @Override
                public void onUserClick(int position) {
                    showUserDetailsDialog(position);
                }
            });
            dataBiding.recyclerSearchResult.setAdapter(publicationAdapter);
        }
    }

    private void showUserDetailsDialog(int position) {
    }

    private void shareUser(int position) {
    }

    private void likePublication(int position) {
        //
        Toast.makeText(activity, "Liked", Toast.LENGTH_SHORT).show();
    }

    private void sharePublication(int position) {
        //
        Toast.makeText(activity, "Shared", Toast.LENGTH_SHORT).show();
    }

    private void goToPostDetailsPage(int position) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, PublicationDetailsFragment.newInstance(mPublications.get(position).getId())).addToBackStack(null).commit();
    }

    private void goToUserDetails(int position) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, PersonDetailFragment.newInstance(mUsers.get(position).get_id())).addToBackStack(null).commit();
    }

    private void doInitializations() {
        publicationViewModel = new ViewModelProvider(activity).get(PublicationViewModel.class);
        userViewModel = new ViewModelProvider(activity).get(UserViewModel.class);
        mUsers = new ArrayList<>();
        mPublications = new ArrayList<>();

        autoCompleteValues = new ArrayList<>();

        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)){
            dataBiding.autoCompleteTextView.setHint(getResources().getString(R.string.search_publication));
        }else{
            if (isProvider){
                dataBiding.autoCompleteTextView.setHint(getResources().getString(R.string.search_provider));
            }else {
                dataBiding.autoCompleteTextView.setHint(getResources().getString(R.string.search_requester));
            }
        }
        dataBiding.autoCompleteTextView.requestFocus();
    }
}