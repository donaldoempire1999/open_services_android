package com.example.openservices.ui.account;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignInBinding;
import com.example.openservices.responses.UserDetailsResponse;
import com.example.openservices.responses.UserSignInResponse;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.utilities.SharedPreferencesManager;
import com.example.openservices.viewmodels.UserViewModel;

public class SignInFragment extends Fragment {

    private FragmentSignInBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    private UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_in, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        userViewModel = new ViewModelProvider(activity).get(UserViewModel.class);
        checkInteractions();

        return view;
    }

    private void checkInteractions() {
        dataBiding.buttonGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
            }
        });
        dataBiding.buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResetPassword();
            }
        });
        dataBiding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToSignIn();
            }
        });
    }

    private void tryToSignIn() {
        String tempPhone = "";
        String tempPassword = "";
        if (dataBiding.editTextPhone.getText() != null)
            tempPhone = dataBiding.editTextPhone.getText().toString();
        if (dataBiding.editTextPassword.getText() != null)
            tempPassword = dataBiding.editTextPassword.getText().toString();
        if (!tempPassword.isEmpty() && !tempPhone.isEmpty()){
            dataBiding.setIsLoadingSignIn(true);
            userViewModel.signIn(tempPhone, tempPassword).observe(activity, new Observer<UserSignInResponse>() {
                @Override
                public void onChanged(UserSignInResponse userSignInResponse) {
                    if (userSignInResponse != null){
                        goToMyProfile(userSignInResponse);
                    }else{
                        dataBiding.setIsLoadingSignIn(false);
                        Toast.makeText(activity, "None account found !", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void goToMyProfile(UserSignInResponse userSignInResponse) {
        userViewModel.getUserInfo(userSignInResponse.getUserId()).observe(activity, new Observer<UserDetailsResponse>() {
            @Override
            public void onChanged(UserDetailsResponse userDetailsResponse) {
                dataBiding.setIsLoadingSignIn(false);
                if (userDetailsResponse != null){
                    Toast.makeText(activity, "Logged !", Toast.LENGTH_SHORT).show();
                    SharedPreferencesManager.saveUserInfo(activity, userSignInResponse.getUserId(), userSignInResponse.getToken(), userDetailsResponse.getUser().getCategory().getRole());
                    if (userDetailsResponse.getUser().getCategory().getRole().equals(ConstantValue.PROFILE_ADMIN)){
                        goToMyAdminProfile();
                    }else if (userDetailsResponse.getUser().getCategory().getRole().equals(ConstantValue.PROFILE_BUSINESS)){
                        goToMyBusinessProfile();
                    }else{
                        goToMyRequesterProfile();
                    }
                }else{
                    Toast.makeText(activity, "None account found !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToMyAdminProfile() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new AdminAccountFragment()).commit();
    }

    private void goToMyRequesterProfile() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new RequesterAccountFragment()).commit();
    }

    private void goToMyBusinessProfile() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new BusinessAccountFragment()).commit();
    }

    private void goToSignUp() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SignUpFragment()).commit();
    }

    private void goToResetPassword() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new ResetPasswordFragment()).commit();
    }
}