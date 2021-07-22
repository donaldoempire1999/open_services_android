package com.example.openservices.ui.account;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpBinding;
import com.example.openservices.databinding.FragmentSignUpPersonBinding;

public class SignUpPersonFragment extends Fragment {

    private FragmentSignUpPersonBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up_person, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        checkInteractions();

        return view;
    }

    private void checkInteractions() {
    }
}