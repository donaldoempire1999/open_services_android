package com.example.openservices.ui.account.onboarding.createaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpAddressQuarterInfoBinding;
import com.example.openservices.models.User;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.SharedPreferencesManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpAddressQuarterInfoFragment extends Fragment {

    private FragmentSignUpAddressQuarterInfoBinding dataBiding;
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
                inflater, R.layout.fragment_sign_up_address_quarter_info, container, false);
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
        String tempCity = "";
        String tempQuarter = "";
        int tempPoBox = 0;
        //Get edit text info
        if (dataBiding.editTextCity.getText() != null)
            tempCity = dataBiding.editTextCity.getText().toString();
        if (dataBiding.editTextQuarter.getText() != null)
            tempQuarter = dataBiding.editTextQuarter.getText().toString();
        if (dataBiding.editTextQuarter.getText() != null){
            try {

                tempPoBox = Integer.parseInt(dataBiding.editTextQuarter.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //Check if inputs are correct
        if (CustomStringChecker.checkStringInput(tempCity, 3) && CustomStringChecker.checkStringInput(tempQuarter, 3)) {
            isCorrectInputs = true;
        } else {
            isCorrectInputs = false;
        }
        if (isCorrectInputs) {
            userToSignUp.getAddress().setCity(tempCity);
            userToSignUp.getAddress().setQuarter(tempQuarter);
            userToSignUp.getAddress().setBp(tempPoBox);
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
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpCvInfoFragment()).commit();
    }

    private void goToPrevPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpAddressCityInfoFragment()).commit();
    }

    private void setViews() {
        dataBiding.setUserToSignUp(userToSignUp);
    }

    private void getData() {
        userToSignUp = SharedPreferencesManager.getSignUpUserInfo(activity);
    }
}