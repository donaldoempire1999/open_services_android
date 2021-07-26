package com.example.openservices.ui.account.onboarding.createaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpAccountInfoBinding;
import com.example.openservices.models.User;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.SharedPreferencesManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpAccountInfoFragment extends Fragment {

    private FragmentSignUpAccountInfoBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    private boolean isCorrectInputs;
    private User userToSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up_account_info, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        getData();
        setViews();
        checkInteractions();

        return view;
    }

    private void checkInteractions() {
        dataBiding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });
        dataBiding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    private void checkInputs() {
        String tempPhone = "";
        String tempPassword1 = "";
        String tempPassword2 = "";
        //Get edit text info
        if (dataBiding.editTextPhone.getText() != null)
            tempPhone = dataBiding.editTextPhone.getText().toString();
        if (dataBiding.editTextPassword.getText() != null)
            tempPassword1 = dataBiding.editTextPassword.getText().toString();
        if (dataBiding.editTextPassword2.getText() != null)
            tempPassword2 = dataBiding.editTextPassword2.getText().toString();
        //Check if inputs are correct
        if (CustomStringChecker.checkStringInput(tempPhone, 2) && CustomStringChecker.checkStringInput(tempPassword1, 6)) {
            isCorrectInputs = tempPassword1.equals(tempPassword2);
        } else {
            isCorrectInputs = false;
        }
        if (isCorrectInputs) {
            userToSignUp.setPhone_number(tempPhone);
            userToSignUp.setPassword(tempPhone);
            SharedPreferencesManager.saveSignUpUserInfo(activity, userToSignUp);
            goToNextPage();
        } else {
            Toast.makeText(activity, "Please enter valid required fields !", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToNextPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpAddressCityInfoFragment()).commit();
    }

    private void setViews() {
        dataBiding.setUserToSignUp(userToSignUp);
    }

    private void getData() {
        userToSignUp = SharedPreferencesManager.getSignUpUserInfo(activity);
    }
}