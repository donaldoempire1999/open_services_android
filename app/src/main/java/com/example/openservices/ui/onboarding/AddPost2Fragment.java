package com.example.openservices.ui.onboarding;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentAddPost2Binding;
import com.example.openservices.databinding.FragmentAddPostBinding;
import com.example.openservices.models.Publication;
import com.example.openservices.models.TaskDescription;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.SharedPreferencesManager;
import com.example.openservices.viewmodels.PublicationViewModel;

public class AddPost2Fragment extends Fragment {

    private FragmentAddPost2Binding dataBiding;
    private FragmentActivity activity;
    private Context context;

    private String userId;
    private Publication publication;
    private PublicationViewModel publicationViewModel;
    private boolean isCorrectInputs;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_post2, container, false);
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
        int tempPriority;
        String tempDifficulty;
        int checkedId = dataBiding.radioGroupDifficulty.getCheckedRadioButtonId();
        if (checkedId == R.id.radio_button_difficulty_low){}
        tempDifficulty = "low";
        if (checkedId == R.id.radio_button_difficulty_medium){}
        tempDifficulty = "medium";
        if (checkedId == R.id.radio_button_difficulty_high){}
        tempDifficulty = "high";

        if (checkedId == R.id.radio_button_priority_low){}
        tempPriority = 0;
        if (checkedId == R.id.radio_button_priority_medium){}
        tempPriority = 1;
        if (checkedId == R.id.radio_button_priority_high){}
        tempPriority = 2;

        int tempBaseAmount = 0;
        //Get edit text info
        if (dataBiding.editTextPostBaseAmount.getText() != null)
            tempBaseAmount = Integer.parseInt(dataBiding.editTextPostBaseAmount.getText().toString());
        if (publication.getTask_description() == null) {
            TaskDescription taskDescription = new TaskDescription();
            taskDescription.setBase_amount(tempBaseAmount);
            taskDescription.setDifficulty(tempDifficulty);
            taskDescription.setPriority(tempPriority);
            publication.setTask_description(taskDescription);
        }else {
            publication.getTask_description().setBase_amount(tempBaseAmount);
            publication.getTask_description().setDifficulty(tempDifficulty);
            publication.getTask_description().setPriority(tempPriority);
        }
        SharedPreferencesManager.savePostAddInfo(activity, publication);
        goToNextPage();
    }

    private void goToNextPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new AddPost3Fragment()).commit();
    }

    private void goToPrevPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new AddPostFragment()).commit();
    }

    private void setViews() {
        dataBiding.setPublication(publication);
    }

    private void getData() {
        publication = SharedPreferencesManager.getPostAddInfo(activity);
    }
}