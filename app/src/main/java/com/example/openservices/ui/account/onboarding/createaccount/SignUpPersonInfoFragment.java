package com.example.openservices.ui.account.onboarding.createaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpPersonInfoBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpPersonInfoFragment extends Fragment {

    private FragmentSignUpPersonInfoBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    boolean isCorrectInputs = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up_person_info, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

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
        dataBiding.buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextPage();
            }
        });
    }

    private void checkInputs() {
        isCorrectInputs = dataBiding.editTextFirstName.getText() != null;
        if (isCorrectInputs){
            goToNextPage();
        }else {
            Toast.makeText(activity, "Please enter all required fields !", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToNextPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpAddressCityInfoFragment()).commit();
    }

    private void goToPrevPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpAddressQuarterInfoFragment()).commit();
    }

    private void setViews() {
    }
}