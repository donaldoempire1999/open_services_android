package com.example.openservices.ui.account;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignInBinding;
import com.example.openservices.databinding.FragmentSignUpBinding;
import com.example.openservices.viewmodels.UserViewModel;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding dataBiding;
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
                inflater, R.layout.fragment_sign_up, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        checkInteractions();

        return view;
    }

    private void checkInteractions() {
        dataBiding.relativeButtonSignUpBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpBusiness();
            }
        });
        dataBiding.relativeButtonSignUpPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpPerson();
            }
        });
        dataBiding.buttonGoToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignIn();
            }
        });
    }

    private void goToSignIn(){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SignInFragment()).commit();
    }

    private void goToSignUpPerson(){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, new SignUpPersonFragment()).addToBackStack(null).commit();
    }

    private void goToSignUpBusiness(){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, new SignUpBusinessFragment()).addToBackStack(null).commit();
    }
}