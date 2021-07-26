package com.example.openservices.ui.account.onboarding.createaccount;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpBusinessInfoBinding;
import com.example.openservices.models.User;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.CustomStringFormat;
import com.example.openservices.utilities.SharedPreferencesManager;

import java.util.Calendar;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpBusinessInfoFragment extends Fragment {

    int tempYear;
    int tempMonth;
    int tempDay;
    private FragmentSignUpBusinessInfoBinding dataBiding;
    private FragmentActivity activity;
    private Context context;
    private boolean isCorrectInputs;
    private User userToSignUp;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up_business_info, container, false);
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
        dataBiding.relativeButtonBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tempYear = calendar.get(Calendar.YEAR);
                tempMonth = calendar.get(Calendar.MONTH);
                tempDay = calendar.get(Calendar.DAY_OF_MONTH);
                tempDay += tempDay;
                showDialogDateTimePicker();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tempMonth = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dataBiding.textChooseBirthDate.setText(date);
            }
        };
    }

    private void showDialogDateTimePicker() {
        datePickerDialog = new DatePickerDialog(
                context,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                onDateSetListener, tempYear, tempMonth, tempDay
        );
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void checkInputs() {
        String tempName = "";
        String tempBirthDate = "";
        //Get edit text info
        if (dataBiding.editTextFirstName.getText() != null)
            tempName = dataBiding.editTextFirstName.getText().toString();
        if (dataBiding.textChooseBirthDate.getText() != null)
            tempBirthDate = dataBiding.textChooseBirthDate.getText().toString();
        //Check if inputs are correct
        if (CustomStringChecker.checkStringInput(tempName, 2) && CustomStringChecker.checkStringInput(tempBirthDate, 2)) {
            isCorrectInputs = true;
        } else {
            isCorrectInputs = false;
        }
        if (isCorrectInputs) {
            userToSignUp.getEntreprise().setName(tempName);
            String tempIsoDate = "";
            tempIsoDate = CustomStringFormat.getDateISO8601(tempYear + "", tempMonth + "", tempDay + "");
            userToSignUp.getEntreprise().setCreation_date(tempIsoDate);
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