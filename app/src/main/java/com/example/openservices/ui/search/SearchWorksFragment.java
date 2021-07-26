package com.example.openservices.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.adapters.PublicationAdapter;
import com.example.openservices.adapters.UserAdapter;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SearchWorksFragment extends Fragment {

    FragmentSearchWorksBinding dataBiding;
    private PublicationViewModel publicationViewModel;
    private UserViewModel userViewModel;

    private FragmentActivity activity;
    private Context context;

    PublicationAdapter publicationAdapter;
    UserAdapter userAdapter;

    //Data
    ArrayList<Publication> mPublications;
    ArrayList<User> mUsers;

    private String searchMatching = ConstantValue.SEARCH_COLLECTION_KEY_WORD_FACETED;
    private String searchType = ConstantValue.SEARCH_TYPE_POSTS;
    private String searchKeyWord = "";
    private String searchCollection = ConstantValue.SEARCH_COLLECTION_PUBLICATIONS;
    private boolean isProvider = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search_works, container, false);
        View view = dataBiding.getRoot();
        context = getContext();
        activity = getActivity();

        if (activity != null) {
            doInitializations();
            loadFilterSettings();
            setupAdapters();
            loadPostsUsers();
            checkInteractions();
        }

        return view;
    }

    private void checkInteractions() {
        dataBiding.imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSearchPage();
            }
        });
        dataBiding.imageButtonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFilter();
            }
        });
    }

    private void showDialogFilter() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(context);
        bottomSheet.setContentView(R.layout.dialog_search_settings);
        bottomSheet.setCancelable(true);
        bottomSheet.setCanceledOnTouchOutside(true);
        bottomSheet.setDismissWithAnimation(true);
        bottomSheet.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);

        loadFilterSettings();
        bottomSheet.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFilterSettings();
                bottomSheet.dismiss();
                Toast.makeText(activity, "Filter settings saved !", Toast.LENGTH_SHORT).show();
                reloadPostUsers();
            }
        });
        RadioGroup radioGroup = bottomSheet.findViewById(R.id.radio_group);
        CheckBox checkBox = bottomSheet.findViewById(R.id.checkbox_autocomplete_search);

        RadioButton radioButtonProviders = bottomSheet.findViewById(R.id.radio_button_providers);
        RadioButton radioButtonRequesters = bottomSheet.findViewById(R.id.radio_button_requesters);
        RadioButton radioButtonPublications = bottomSheet.findViewById(R.id.radio_button_publications);

        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)){
            assert radioButtonProviders != null;
            radioButtonProviders.setChecked(false);
            assert radioButtonRequesters != null;
            radioButtonRequesters.setChecked(false);
            assert radioButtonPublications != null;
            radioButtonPublications.setChecked(true);
        }else {
            if (isProvider){
                assert radioButtonProviders != null;
                radioButtonProviders.setChecked(true);
                assert radioButtonRequesters != null;
                radioButtonRequesters.setChecked(false);
            }else{
                assert radioButtonProviders != null;
                radioButtonProviders.setChecked(false);
                assert radioButtonRequesters != null;
                radioButtonRequesters.setChecked(true);
            }
            assert radioButtonPublications != null;
            radioButtonPublications.setChecked(false);
        }
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radio_button_providers){
                        searchCollection = ConstantValue.SEARCH_COLLECTION_USERS;
                        searchType = ConstantValue.SEARCH_TYPE_USERS;
                        isProvider = true;
                    }else if (checkedId == R.id.radio_button_requesters){
                        searchCollection = ConstantValue.SEARCH_COLLECTION_USERS;
                        searchType = ConstantValue.SEARCH_TYPE_USERS;
                        isProvider = false;
                    }else {
                        searchCollection = ConstantValue.SEARCH_COLLECTION_PUBLICATIONS;
                        searchType = ConstantValue.SEARCH_TYPE_POSTS;
                    }
                }
            });
        }
        if (checkBox != null){
            if (searchMatching.equals(ConstantValue.SEARCH_COLLECTION_KEY_WORD_AUTOCOMPLETE)){
                checkBox.setChecked(true);
            }else{
                checkBox.setChecked(false);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        searchMatching = ConstantValue.SEARCH_COLLECTION_KEY_WORD_AUTOCOMPLETE;
                    }else{
                        searchMatching = ConstantValue.SEARCH_COLLECTION_KEY_WORD_FACETED;
                    }
                }
            });
        }
        bottomSheet.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
            }
        });
        bottomSheet.show();
    }

    private void loadFilterSettings() {
        searchType = SharedPreferencesManager.getSearchType(activity);
        searchCollection = SharedPreferencesManager.getSearchCollection(activity);
        searchMatching = SharedPreferencesManager.getSearchMatching(activity);
        isProvider = SharedPreferencesManager.getIsProvider(activity);
    }

    private void saveFilterSettings() {
        SharedPreferencesManager.saveSearchType(activity, searchType);
        SharedPreferencesManager.saveSearchCollection(activity, searchCollection);
        SharedPreferencesManager.saveSearchMatching(activity, searchMatching);
        SharedPreferencesManager.saveIsProvider(activity, isProvider);
    }

    private void gotoSearchPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.main_frame_layout, SearchResultsFragment.newInstance(searchKeyWord, searchType, searchMatching, searchCollection, isProvider)).addToBackStack(null).commit();
    }

    private void reloadPostUsers(){
        mUsers = new ArrayList<>();
        mPublications = new ArrayList<>();
        setupAdapters();
        loadPostsUsers();
    }

    private void loadPostsUsers() {
        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)){
            getPublications();
        }else{
            if (isProvider){
                getProvidersUsers();
            }else{
                getRequestersUsers();
            }
        }
    }

    private void getRequestersUsers() {
        mPublications.clear();
        mUsers.clear();
        dataBiding.setIsLoadingMore(true);
        userViewModel.getAllUsers().observe(activity, new Observer<ArrayList<User>>() {
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
                            loadMoreUsers(20);
                        }else {
                            mUsers.clear();
                            dataBiding.setIsLoadingMore(false);
                        }
                    }else{
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS))
                            userAdapter.notifyDataSetChanged();
                        dataBiding.setIsLoadingMore(false);
                    }
                }
            }
        });
    }

    private void getProvidersUsers() {
        mPublications.clear();
        mUsers.clear();
        dataBiding.setIsLoadingMore(true);
        userViewModel.getAllUsers().observe(activity, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (users != null) {
                    if (users.size() > 0) {
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_USERS)) {
                            for (User user : users){
                                if (user.getCategory().getRole().equals(ConstantValue.PROFILE_BUSINESS)){
                                    mUsers.add(user);
                                }
                            }
                            loadMoreUsers(20);
                        }else {
                            mUsers.clear();
                            dataBiding.setIsLoadingMore(false);
                        }
                    }else{
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS))
                            userAdapter.notifyDataSetChanged();
                        dataBiding.setIsLoadingMore(false);
                    }
                }
            }
        });
    }

    private void loadMoreUsers(int qte) {
        int tempSize = mUsers.size();
        userAdapter.notifyItemRangeInserted(mUsers.size(), mUsers.size()+qte);
        dataBiding.setIsLoadingMore(false);
    }

    private void getPublications() {
        mUsers.clear();
        mPublications.clear();
        dataBiding.setIsLoadingMore(true);
        publicationViewModel.getAllPublications().observe(activity, new Observer<PublicationResponse>() {
            @Override
            public void onChanged(PublicationResponse publicationResponse) {
                if (publicationResponse != null) {
                    if (publicationResponse.getPublications() != null) {
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS)) {
                            mPublications.addAll(publicationResponse.getPublications());
                            loadMorePublications(20);
                        }else {
                            mPublications.clear();
                            dataBiding.setIsLoadingMore(false);
                        }
                    }else{
                        if (searchType.equals(ConstantValue.SEARCH_TYPE_POSTS))
                            publicationAdapter.notifyDataSetChanged();
                        dataBiding.setIsLoadingMore(false);
                    }
                }
            }
        });
    }

    private void loadMorePublications(int qte) {
        int tempSize = mPublications.size();
        publicationAdapter.notifyItemRangeInserted(mPublications.size(), mPublications.size()+qte);
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
        publicationViewModel = new ViewModelProvider(activity).get(PublicationViewModel.class);
        userViewModel = new ViewModelProvider(activity).get(UserViewModel.class);
        mUsers = new ArrayList<>();
        mPublications = new ArrayList<>();
    }
}