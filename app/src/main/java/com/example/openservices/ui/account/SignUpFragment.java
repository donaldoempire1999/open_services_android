package com.example.openservices.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentSignUpBinding;
import com.example.openservices.models.Address;
import com.example.openservices.models.Category;
import com.example.openservices.models.Cv;
import com.example.openservices.models.Entreprise;
import com.example.openservices.models.Person;
import com.example.openservices.models.User;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.utilities.SharedPreferencesManager;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();

        checkInteractions();

        return view;
    }

    private void checkInteractions() {
        dataBiding.relativeButtonSignUpBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpBusiness();
            }
        });
        dataBiding.relativeButtonSignUpPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpPerson();
            }
        });
        dataBiding.buttonGoToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignIn();
            }
        });
    }

    private void goToSignIn() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SignInFragment()).commit();
    }

    private void goToSignUpPerson() {
        User newUser = new User();
        newUser.setAddress(new Address());
        newUser.setCv(new Cv());
        newUser.setContracts(new ArrayList<>());
        newUser.setEntreprise(new Entreprise());
        newUser.setPerson(new Person());

        Category newCategory = new Category();
        newCategory.setRole(ConstantValue.PROFILE_REQUESTER);
        newUser.setCategory(newCategory);

        SharedPreferencesManager.saveSignUpUserInfo(activity, newUser);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, new SignUpPersonFragment()).addToBackStack(null).commit();
    }

    private void goToSignUpBusiness() {
        User newUser = new User();
        newUser.setAddress(new Address());
        newUser.setCv(new Cv());
        newUser.setContracts(new ArrayList<>());
        newUser.setEntreprise(new Entreprise());
        newUser.setPerson(new Person());

        Category newCategory = new Category();
        newCategory.setRole(ConstantValue.PROFILE_BUSINESS);
        newUser.setCategory(newCategory);

        SharedPreferencesManager.saveSignUpUserInfo(activity, newUser);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, new SignUpBusinessFragment()).addToBackStack(null).commit();
    }
}