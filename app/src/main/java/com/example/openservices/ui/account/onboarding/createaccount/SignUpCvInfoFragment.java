package com.example.openservices.ui.account.onboarding.createaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpCvInfoBinding;
import com.example.openservices.models.User;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.SharedPreferencesManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpCvInfoFragment extends Fragment {

    private FragmentSignUpCvInfoBinding dataBiding;
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
                inflater, R.layout.fragment_sign_up_cv_info, container, false);
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
        dataBiding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPrevPage();
            }
        });
    }

    private void checkInputs() {
        String tempMainActivity = "";
        String tempTitle = "";
        //Get edit text info
        if (dataBiding.editTextMainActivity.getText() != null)
            tempMainActivity = dataBiding.editTextMainActivity.getText().toString();
        if (dataBiding.editTextTitleActivity.getText() != null)
            tempTitle = dataBiding.editTextTitleActivity.getText().toString();
        //Check if inputs are correct
        if (CustomStringChecker.checkStringInput(tempMainActivity, 3) && CustomStringChecker.checkStringInput(tempTitle, 3)) {
            isCorrectInputs = true;
        } else {
            isCorrectInputs = false;
        }
        if (isCorrectInputs) {
            userToSignUp.getCv().setMain_activity(tempMainActivity);
            userToSignUp.getCv().setTitle(tempTitle);
            SharedPreferencesManager.saveSignUpUserInfo(activity, userToSignUp);
            goToNextPage();
        } else {
            Toast.makeText(activity, "Please enter valid required fields !", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToNextPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.pop_exit);
        if (userToSignUp.getCategory().getRole().equals(ConstantValue.PROFILE_BUSINESS)) {
            fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpBusinessInfoFragment()).commit();
        } else {
            fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpPersonInfoFragment()).commit();
        }
    }

    private void goToPrevPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpAddressQuarterInfoFragment()).commit();
    }

    private void setViews() {
        dataBiding.setUserToSignUp(userToSignUp);
    }

    private void getData() {
        userToSignUp = SharedPreferencesManager.getSignUpUserInfo(activity);
    }
}