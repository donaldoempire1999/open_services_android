package com.example.openservices.ui.persondetail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentPersonDetailBinding;
import com.example.openservices.models.User;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.viewmodels.UserViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class PersonDetailFragment extends Fragment {

    private FragmentPersonDetailBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    private String userId;

    private User currentUser;
    private UserViewModel userViewModel;

    public PersonDetailFragment() {
    }

    public static PersonDetailFragment newInstance(String userId) {
        PersonDetailFragment fragment = new PersonDetailFragment();
        Bundle args = new Bundle();
        args.putString(ConstantValue.ARG_PARAM_PERSON_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ConstantValue.ARG_PARAM_PERSON_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_person_detail, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        getData();
        setViews();
        checkInteractions();

        return view;
    }

    private void checkInteractions() {
        dataBiding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        dataBiding.buttonContactInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContact();
            }
        });
    }

    private void showContact() {
        //
    }

    private void setViews() {
        dataBiding.setUser(currentUser);
    }

    private void getData() {
        currentUser = new User();
    }
}