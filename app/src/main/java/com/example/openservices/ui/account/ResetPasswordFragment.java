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
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentResetPasswordBinding;
import com.example.openservices.databinding.FragmentSignInBinding;
import com.example.openservices.viewmodels.UserViewModel;

public class ResetPasswordFragment extends Fragment {

    private FragmentResetPasswordBinding dataBiding;
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
                inflater, R.layout.fragment_reset_password, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        checkInteractions();

        return view;
    }

    private void checkInteractions() {
        dataBiding.buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToResetPassword();
            }
        });
        dataBiding.buttonGoToSignUp.setOnClickListener(new View.OnClickListener() {
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

    private void tryToResetPassword() {
        if (dataBiding.editTextEmail.getText() != null && !dataBiding.editTextEmail.getText().toString().isEmpty()){
            boolean isValid = true;
            if (isValid){
                dataBiding.setIsLoadingReset(true);
            }else{
                Toast.makeText(activity, "Please enter valid email !", Toast.LENGTH_SHORT).show();
                dataBiding.setIsLoadingReset(false);
            }
        }
    }
}