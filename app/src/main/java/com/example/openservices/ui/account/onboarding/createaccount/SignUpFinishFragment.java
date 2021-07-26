package com.example.openservices.ui.account.onboarding.createaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpFinishBinding;
import com.example.openservices.models.User;
import com.example.openservices.responses.UserSignUpResponse;
import com.example.openservices.utilities.SharedPreferencesManager;
import com.example.openservices.viewmodels.UserViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SignUpFinishFragment extends Fragment {

    private FragmentSignUpFinishBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    private User userToSignUp;
    private UserViewModel userViewModel;
    private boolean isLoadingSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up_finish, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        userViewModel = new ViewModelProvider(activity).get(UserViewModel.class);
        getData();
        checkInteractions();
        signUpUser();

        return view;
    }

    private void signUpUser() {
        isLoadingSignUp = true;
        userViewModel.signUp(userToSignUp).observe(activity, new Observer<UserSignUpResponse>() {
            @Override
            public void onChanged(UserSignUpResponse userSignUpResponse) {
                isLoadingSignUp = false;
                if (userSignUpResponse != null) {
                    if (userSignUpResponse.getMessage() != null && userSignUpResponse.getMessage().equals("Successful created user!!")) {
                        dataBiding.setResponseMessage(userSignUpResponse.getMessage());
                        dataBiding.textSignUpResponse.setTextColor(activity.getResources().getColor(R.color.green_200));
                        activity.onBackPressed();
                    } else {
                        String tempResponse = R.string.please_check_your_internet_connexion + " !";
                        dataBiding.setResponseMessage(tempResponse);
                        dataBiding.textSignUpResponse.setTextColor(activity.getResources().getColor(R.color.red_200));
                    }
                } else {
                    String tempResponse = R.string.unknown_error + " !";
                    dataBiding.setResponseMessage(tempResponse);
                    dataBiding.textSignUpResponse.setTextColor(activity.getResources().getColor(R.color.red_200));
                }
            }
        });
    }

    private void checkInteractions() {
        dataBiding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSignUp();
            }
        });
        dataBiding.buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrySignUp();
            }
        });
    }

    private void retrySignUp() {
        signUpUser();
    }

    private void cancelSignUp() {
        isLoadingSignUp = false;
        activity.onBackPressed();
    }

    private void getData() {
        userToSignUp = SharedPreferencesManager.getSignUpUserInfo(activity);
    }
}