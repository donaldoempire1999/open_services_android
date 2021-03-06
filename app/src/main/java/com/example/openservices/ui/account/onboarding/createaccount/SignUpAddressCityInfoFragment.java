package com.example.openservices.ui.account.onboarding.createaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpAddressCityInfoBinding;
import com.example.openservices.models.User;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.SharedPreferencesManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpAddressCityInfoFragment extends Fragment {

    private FragmentSignUpAddressCityInfoBinding dataBiding;
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
                inflater, R.layout.fragment_sign_up_address_city_info, container, false);
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
        String tempCountry = "";
        String tempRegion = "";
        //Get edit text info
        if (dataBiding.editTextCountry.getText() != null)
            tempCountry = dataBiding.editTextCountry.getText().toString();
        if (dataBiding.editTextRegion.getText() != null)
            tempRegion = dataBiding.editTextRegion.getText().toString();
        //Check if inputs are correct
        if (CustomStringChecker.checkStringInput(tempCountry, 2) && CustomStringChecker.checkStringInput(tempRegion, 2)) {
            isCorrectInputs = true;
        } else {
            isCorrectInputs = false;
        }
        if (isCorrectInputs) {
            userToSignUp.getAddress().setCountry(tempCountry);
            userToSignUp.getAddress().setRegion(tempRegion);
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
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpAddressQuarterInfoFragment()).commit();
    }

    private void goToPrevPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpAccountInfoFragment()).commit();
    }

    private void setViews() {
        dataBiding.setUserToSignUp(userToSignUp);
    }

    private void getData() {
        userToSignUp = SharedPreferencesManager.getSignUpUserInfo(activity);
    }
}