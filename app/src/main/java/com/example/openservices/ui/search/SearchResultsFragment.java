package com.example.openservices.ui.search;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.adapters.PublicationAdapter;
import com.example.openservices.adapters.UserAdapter;
import com.example.openservices.databinding.FragmentSearchResultsBinding;
import com.example.openservices.databinding.FragmentSearchWorksBinding;
import com.example.openservices.models.Publication;
import com.example.openservices.models.User;
import com.example.openservices.responses.PublicationResponse;
import com.example.openservices.ui.persondetail.PersonDetailFragment;
import com.example.openservices.ui.publicationdetail.PublicationDetailsFragment;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.utilities.SharedPreferencesManager;
import com.example.openservices.viewmodels.PublicationViewModel;
import com.example.openservices.viewmodels.UserViewModel;

import java.util.ArrayList;

public class SearchResultsFragment extends Fragment {

    FragmentSearchResultsBinding dataBiding;
    private PublicationViewModel publicationViewModel;
    private UserViewModel userViewModel;

    private FragmentActivity activity;
    private Context context;

    PublicationAdapter publicationAdapter;
    UserAdapter userAdapter;

    //Data
    ArrayList<Publication> mPublications;
    ArrayList<User> mUsers;
    ArrayList<User> autoCompleteValues;

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
            setupAdapters();
            checkInteractions();
        }

        return view;
    }

    private void checkInteractions() {
        dataBiding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        dataBiding.autoCompleteSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadPostsUsers();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
        dataBiding.imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPostsUsers();
            }
        });
    }

    private void loadPostsUsers() {
        if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)){;
            if (isProvider){
                searchProvidersUsers(searchMatching, searchCollection, searchKeyWord);
            }else{
                searchRequestersUsers(searchMatching, searchCollection, searchKeyWord);
            }
        }else{
            searchPublications(searchMatching, searchCollection, searchKeyWord);
        }
    }

    private void searchRequestersUsers(String searchMatching, String searchCollection, String searchKeyWord) {
        mPublications.clear();
        mUsers.clear();
        dataBiding.setIsLoadingMore(true);
        userViewModel.searchUsers(searchMatching, searchCollection, searchKeyWord).observe(activity, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (users != null) {
                    if (users.size() > 0) {
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
                            for (User user : users){
                                if (user.getCategory().getRole().equals(ConstantValue.PROFILE_REQUESTER)){
                                    mUsers.add(user);
                                }
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
        });
    }

    private void searchProvidersUsers(String searchMatching, String searchCollection, String searchKeyWord) {
        mPublications.clear();
        mUsers.clear();
        dataBiding.setIsLoadingMore(true);
        userViewModel.searchUsers(searchMatching, searchCollection, searchKeyWord).observe(activity, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (users != null) {
                    if (users.size() > 0) {
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
                            Toast.makeText(activity, "Size : "+users.size(), Toast.LENGTH_SHORT).show();
//                            for (User user : users){
//                                if (user.getCategory().getRole().equals(ConstantValue.PROFILE_BUSINESS)){
//                                    mUsers.add(user);
//                                }
//                            }
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
        });
    }

    private void loadMoreUsers() {
        int tempSize = mUsers.size();
        Toast.makeText(activity, tempSize+"", Toast.LENGTH_SHORT).show();
        userAdapter.notifyDataSetChanged();
        dataBiding.setIsLoadingMore(false);
    }

    private void searchPublications(String searchMatching, String searchCollection, String searchKeyWord) {
        mUsers.clear();
        mPublications.clear();
        dataBiding.setIsLoadingMore(true);
        publicationViewModel.searchPublications(searchMatching, searchCollection, searchKeyWord).observe(activity, new Observer<ArrayList<Publication>>() {
            @Override
            public void onChanged(ArrayList<Publication> publications) {
                dataBiding.setIsLoadingMore(false);
                if (publications != null) {
                    Toast.makeText(activity, "Searching Posts", Toast.LENGTH_SHORT).show();
                    if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)) {
                        mPublications.addAll(publications);
                        loadMorePublications();
                    }else{
                        mPublications.clear();
                        dataBiding.setIsLoadingMore(false);
                    }
                }else{
                    Toast.makeText(activity, "No posts", Toast.LENGTH_SHORT).show();
                    dataBiding.setIsLoadingMore(false);
                }
            }
        });
    }

    private void loadMorePublications() {
        publicationAdapter.notifyDataSetChanged();
        dataBiding.setIsLoadingMore(false);
    }

    private void setupAdapters() {
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
        fragmentTransaction.add(R.id.main_frame_layout, PublicationDetailsFragment.newInstance(mPublications.get(position).getAuthor())).addToBackStack(null).commit();
    }

    private void goToUserDetails(int position) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, PersonDetailFragment.newInstance(mUsers.get(position).get_id())).addToBackStack(null).commit();
    }

    private void doInitializations() {
        autoCompleteValues = new ArrayList<>();
        publicationViewModel = new ViewModelProvider(activity).get(PublicationViewModel.class);
        userViewModel = new ViewModelProvider(activity).get(UserViewModel.class);
        mUsers = new ArrayList<>();
        mPublications = new ArrayList<>();
        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)){
            dataBiding.autoCompleteSearchView.setHint(getResources().getString(R.string.search_publication));
        }else{
            if (isProvider){
                dataBiding.autoCompleteSearchView.setHint(getResources().getString(R.string.search_provider));
            }else {
                dataBiding.autoCompleteSearchView.setHint(getResources().getString(R.string.search_requester));
            }
        }
        dataBiding.autoCompleteSearchView.requestFocus();
    }
}