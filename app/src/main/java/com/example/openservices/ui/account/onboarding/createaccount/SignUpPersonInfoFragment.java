package com.example.openservices.ui.account.onboarding.createaccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpPersonInfoBinding;
import com.example.openservices.models.User;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.CustomStringFormat;
import com.example.openservices.utilities.SharedPreferencesManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpPersonInfoFragment extends Fragment {

    boolean isCorrectInputs;
    int tempYear;
    int tempMonth;
    int tempDay;
    private FragmentSignUpPersonInfoBinding dataBiding;
    private FragmentActivity activity;
    private Context context;
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
                inflater, R.layout.fragment_sign_up_person_info, container, false);
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
        dataBiding.buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextPage();
            }
        });
    }

    private void checkInputs() {
        String tempFirstName = "";
        String tempLastName = "";
        String tempBirthDate = "";
        //Get edit text info
        if (dataBiding.editTextFirstName.getText() != null)
            tempFirstName = dataBiding.editTextFirstName.getText().toString();
        if (dataBiding.editTextLastName.getText() != null)
            tempLastName = dataBiding.editTextLastName.getText().toString();
        if (dataBiding.textChooseBirthDate.getText() != null)
            tempBirthDate = dataBiding.textChooseBirthDate.getText().toString();
        //Check if inputs are correct
        if (CustomStringChecker.checkStringInput(tempFirstName, 2) && CustomStringChecker.checkStringInput(tempLastName, 2) && CustomStringChecker.checkStringInput(tempBirthDate, 2)) {
            isCorrectInputs = true;
        } else {
            isCorrectInputs = false;
        }
        if (isCorrectInputs) {
            userToSignUp.getPerson().setFirst_name(tempFirstName);
            userToSignUp.getPerson().setSecond_name(tempLastName);
            String tempIsoDate = "";
            tempIsoDate = CustomStringFormat.getDateISO8601(tempYear + "", tempMonth + "", tempDay + "");
            userToSignUp.getPerson().setBirthday(tempIsoDate);
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
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpMailInfoFragment()).commit();
    }

    private void goToPrevPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new SignUpCvInfoFragment()).commit();
    }

    private void setViews() {
        dataBiding.setUserToSignUp(userToSignUp);
    }

    private void getData() {
        userToSignUp = SharedPreferencesManager.getSignUpUserInfo(activity);
    }
}