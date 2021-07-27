package com.example.openservices.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.databinding.FragmentProviderAccountBinding;
import com.example.openservices.databinding.FragmentSignUpBusinessBinding;
import com.example.openservices.models.User;
import com.example.openservices.responses.UserDetailsResponse;
import com.example.openservices.ui.account.onboarding.createaccount.SignUpAccountInfoFragment;
import com.example.openservices.ui.onboarding.AddPostFragment;
import com.example.openservices.ui.search.SearchWorksFragment;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.utilities.SharedPreferencesManager;
import com.example.openservices.viewmodels.UserViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ProviderAccountFragment extends Fragment {

    private FragmentProviderAccountBinding dataBiding;
    private FragmentActivity activity;
    private Context context;

    private String userId;

    private User currentUser;
    private UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBiding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_provider_account, container, false);
        View view = dataBiding.getRoot();
        activity = getActivity();
        context = getContext();


        if (activity != null) {
            doInitializations();
            getUserInfoInfo();
            setViews();
            checkInteractions();
        }

        return view;
    }


    private void getUserInfoInfo() {
        dataBiding.setIsLoadingInfo(true);
        dataBiding.linearPleaseConnect.setVisibility(View.GONE);
        dataBiding.nestedScrollView.setVisibility(View.GONE);
        userViewModel.getUserInfo(userId).observe(activity, new Observer<UserDetailsResponse>() {
            @Override
            public void onChanged(UserDetailsResponse userDetailsResponse) {
                if (userDetailsResponse != null){
                    if (userDetailsResponse.getUser() != null){
                        currentUser = userDetailsResponse.getUser();
                        dataBiding.linearPleaseConnect.setVisibility(View.GONE);
                        dataBiding.nestedScrollView.setVisibility(View.VISIBLE);
                        setViews();
                    }else{
                        dataBiding.linearPleaseConnect.setVisibility(View.VISIBLE);
                        dataBiding.nestedScrollView.setVisibility(View.GONE);
                        Toast.makeText(activity, "No result !", Toast.LENGTH_SHORT).show();
                    }
                    dataBiding.setIsLoadingInfo(false);
                }else {
                    dataBiding.linearPleaseConnect.setVisibility(View.VISIBLE);
                    dataBiding.nestedScrollView.setVisibility(View.GONE);
                    Toast.makeText(activity, "Unautorized !", Toast.LENGTH_SHORT).show();
                    currentUser = null;
                }
                dataBiding.setIsLoadingInfo(false);
                setViews();
            }
        });
    }

    private void doInitializations() {
        userId = SharedPreferencesManager.getUserInfo(activity).get(0);
        ConstantValue.setTOKEN(SharedPreferencesManager.getUserInfo(activity).get(1));
        userViewModel = new ViewModelProvider(activity).get(UserViewModel.class);
    }

    private void checkInteractions() {
        dataBiding.imageButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOutUser();
            }
        });
        dataBiding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });
        dataBiding.buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPost();
            }
        });
    }

    private void addNewPost() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.add(R.id.main_frame_layout, new AddPostFragment()).addToBackStack(null).commit();
    }

    private void logOutUser() {
        SharedPreferencesManager.saveUserInfo(activity, null, null, null);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SignInFragment()).commit();
    }

    private void editProfile() {
        //
        Toast.makeText(activity, "Edit work in progress", Toast.LENGTH_SHORT).show();
    }

    private void setViews() {
        dataBiding.setUser(currentUser);
    }
}