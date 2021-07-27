package com.example.openservices.ui.publicationdetail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentPublicationDetailsBinding;
import com.example.openservices.models.PublicationDetail;
import com.example.openservices.models.User;
import com.example.openservices.responses.PublicationDetailsResponse;
import com.example.openservices.responses.UserDetailsResponse;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.viewmodels.PublicationViewModel;
import com.example.openservices.viewmodels.UserViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class PublicationDetailsFragment extends Fragment {

    private FragmentPublicationDetailsBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    private String publicationId;

    private PublicationDetail currentPost;
    private User currentUser;
    private PublicationViewModel publicationViewModel;
    private UserViewModel userViewModel;


    public static PublicationDetailsFragment newInstance(String publicationId) {
        PublicationDetailsFragment fragment = new PublicationDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ConstantValue.ARG_PARAM_PUBLICATION_ID, publicationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            publicationId = getArguments().getString(ConstantValue.ARG_PARAM_PUBLICATION_ID);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_publication_details, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        if (activity != null) {
            doInitializations();
            getPublicationInfo();
            checkInteractions();
        }

        return view;
    }

    private void checkInteractions() {
        dataBiding.buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        dataBiding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        dataBiding.buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFollower();
            }
        });
        dataBiding.buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportPost();
            }
        });
        dataBiding.imageButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favPost();
            }
        });
    }

    private void favPost() {
        //
        Toast.makeText(activity, "Fav", Toast.LENGTH_SHORT).show();
    }

    private void reportPost() {
        //
        Toast.makeText(activity, "Report", Toast.LENGTH_SHORT).show();
    }

    private void addFollower() {
        //
        Toast.makeText(activity, "Follow", Toast.LENGTH_SHORT).show();
    }

    private void showContact() {
        //
        Toast.makeText(activity, "Contact", Toast.LENGTH_SHORT).show();
    }

    private void getPublicationInfo(){
        dataBiding.setIsLoadingView(true);
        dataBiding.linearPleaseConnect.setVisibility(View.GONE);
        dataBiding.nestedScrollView.setVisibility(View.GONE);
        dataBiding.linearApplyCandidate.setVisibility(View.GONE);
        publicationViewModel.getPublicationInfo(publicationId).observe(activity, new Observer<PublicationDetailsResponse>() {
            @Override
            public void onChanged(PublicationDetailsResponse publicationDetailsResponse) {
                if (publicationDetailsResponse != null){
                    if (publicationDetailsResponse.getPublications() != null && publicationDetailsResponse.getPublications().size() > 0){
                        currentPost = publicationDetailsResponse.getPublications().get(0);
                        if (currentPost != null) {
                            getAuthorInfo(currentPost.getAuthor());
                        }else{
                            dataBiding.setIsLoadingView(false);
                            dataBiding.linearPleaseConnect.setVisibility(View.VISIBLE);
                            dataBiding.nestedScrollView.setVisibility(View.GONE);
                            dataBiding.linearApplyCandidate.setVisibility(View.GONE);
                            Toast.makeText(activity, "Empty result !", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        dataBiding.setIsLoadingView(false);
                        dataBiding.linearPleaseConnect.setVisibility(View.VISIBLE);
                        dataBiding.nestedScrollView.setVisibility(View.GONE);
                        dataBiding.linearApplyCandidate.setVisibility(View.GONE);
                        Toast.makeText(activity, "No result !", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    dataBiding.setIsLoadingView(false);
                    dataBiding.linearPleaseConnect.setVisibility(View.VISIBLE);
                    dataBiding.nestedScrollView.setVisibility(View.GONE);
                    dataBiding.linearApplyCandidate.setVisibility(View.GONE);
                    Toast.makeText(activity, "Unautorized !", Toast.LENGTH_SHORT).show();
                    currentPost = null;
                }
                setViews();
            }
        });
    }

    private void getAuthorInfo(String author) {
        userViewModel.getUserInfo(author).observe(activity, new Observer<UserDetailsResponse>() {
            @Override
            public void onChanged(UserDetailsResponse userDetailsResponse) {
                dataBiding.setIsLoadingView(false);
                if (userDetailsResponse != null){
                    currentUser = userDetailsResponse.getUser();
                    dataBiding.linearPleaseConnect.setVisibility(View.GONE);
                    dataBiding.nestedScrollView.setVisibility(View.VISIBLE);
                    dataBiding.linearApplyCandidate.setVisibility(View.VISIBLE);
                }else{
                    currentUser = null;
                    dataBiding.linearPleaseConnect.setVisibility(View.VISIBLE);
                    dataBiding.nestedScrollView.setVisibility(View.GONE);
                    dataBiding.linearApplyCandidate.setVisibility(View.GONE);
                    Toast.makeText(activity, "Unautorized !", Toast.LENGTH_SHORT).show();
                }
                setViews();
            }
        });
    }

    private void doInitializations() {
        publicationViewModel = new ViewModelProvider(activity).get(PublicationViewModel.class);
        userViewModel = new ViewModelProvider(activity).get(UserViewModel.class);
    }

    private void setViews() {
        dataBiding.setPublication(currentPost);
        dataBiding.setUser(currentUser);
    }
}