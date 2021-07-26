package com.example.openservices.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.ui.account.AdminAccountFragment;
import com.example.openservices.ui.account.BusinessAccountFragment;
import com.example.openservices.ui.account.RequesterAccountFragment;
import com.example.openservices.ui.account.SignInFragment;
import com.example.openservices.ui.contracts.ContractFragment;
import com.example.openservices.ui.search.SearchWorksFragment;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.utilities.SharedPreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {

    private BottomNavigationView navigation;
    private FragmentActivity activity;
    private Context context;

    private boolean isUserLoaded;
    private String userCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        activity = getActivity();
        context = getContext();
        navigation = (BottomNavigationView) view.findViewById(R.id.bottom_nav_view);
        navigation.setSelectedItemId(R.id.bottom_menu_search);
        getData();
        checkFragment();
        return view;
    }

    @Override
    public void onResume() {
        getData();
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        getData();
        super.onViewStateRestored(savedInstanceState);
    }

    private void getData() {
        ArrayList<String> tempResult = SharedPreferencesManager.getUserInfo(activity);
        if (!tempResult.isEmpty()) {
            isUserLoaded = true;
            userCategory = tempResult.get(2);
        } else {
            isUserLoaded = false;
        }
    }

    private void checkFragment() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SearchWorksFragment()).commit();
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                getData();
                if (getActivity() != null) {
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    if (item.getItemId() == R.id.bottom_menu_contract) {
                        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new ContractFragment()).commit();
                        return true;
                    } else if (item.getItemId() == R.id.bottom_menu_search) {
                        fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SearchWorksFragment()).commit();
                        return true;
                    } else if (item.getItemId() == R.id.bottom_menu_account) {
                        if (isUserLoaded) {
                            if (userCategory != null) {
                                if (userCategory.equals(ConstantValue.PROFILE_ADMIN)) {
                                    fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new AdminAccountFragment()).commit();
                                } else if (userCategory.equals(ConstantValue.PROFILE_BUSINESS)) {
                                    fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new BusinessAccountFragment()).commit();
                                } else if (userCategory.equals(ConstantValue.PROFILE_REQUESTER)) {
                                    fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new RequesterAccountFragment()).commit();
                                } else {
                                    fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SignInFragment()).commit();
                                }
                            } else {
                                fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SignInFragment()).commit();
                            }
                        } else {
                            fragmentTransaction.replace(R.id.bottom_nav_frame_layout, new SignInFragment()).commit();
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
}