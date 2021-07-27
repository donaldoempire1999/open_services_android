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
import com.example.openservices.databinding.FragmentAddPost3Binding;
import com.example.openservices.databinding.FragmentAddPostBinding;
import com.example.openservices.models.Publication;
import com.example.openservices.models.TaskDescription;
import com.example.openservices.utilities.CustomStringChecker;
import com.example.openservices.utilities.SharedPreferencesManager;
import com.example.openservices.viewmodels.PublicationViewModel;

public class AddPost3Fragment extends Fragment {

    private FragmentAddPost3Binding dataBiding;
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
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_post3, container, false);
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
//        String tempTitle = "";
//        //Get edit text info
//        if (dataBiding.editTextPostTitle.getText() != null)
//            tempTitle = dataBiding.editTextPostTitle.getText().toString();
//        String tempDesc = "";
//        //Get edit text info
//        if (dataBiding.editTextPostDescription.getText() != null)
//            tempDesc = dataBiding.editTextPostDescription.getText().toString();
//        //Check if inputs are correct
//        isCorrectInputs = CustomStringChecker.checkStringInput(tempTitle, 20) && CustomStringChecker.checkStringInput(tempDesc, 20);
//        if (isCorrectInputs) {
//            if (publication.getTask_description() == null) {
//                TaskDescription taskDescription = new TaskDescription();
//                taskDescription.setTitle(tempTitle);
//                taskDescription.setDescription(tempDesc);
//                publication.setTask_description(taskDescription);
//            }else {
//                publication.getTask_description().setTitle(tempTitle);
//                publication.getTask_description().setDescription(tempDesc);
//            }
//            SharedPreferencesManager.savePostAddInfo(activity, publication);
//            goToNextPage();
//        } else {
//            Toast.makeText(activity, "Please enter valid required fields !", Toast.LENGTH_SHORT).show();
//        }
    }

    private void goToNextPage() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_trans, R.anim.exit, R.anim.pop_enter_trans, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.frame_layout_onboarding, new AddPost2Fragment()).commit();
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