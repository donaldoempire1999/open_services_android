package com.example.openservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.openservices.ui.accountfragment.AccountFragment;
import com.example.openservices.ui.contractfragment.ContractFragment;
import com.example.openservices.ui.jobsfragment.JobsFragment;
import com.example.openservices.ui.messagefragment.ChatFragment;
import com.example.openservices.ui.searchfragment.SearchFragment;
import com.example.openservices.ui.signinfragment.SignInFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        checkFragment();
    }

    private void checkFragment(){
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (item.getItemId() == R.id.bottom_menu_contract){
                    fragmentTransaction.replace(R.id.main_frame_layout, new ContractFragment()).commit();
                    return true;
                }else if (item.getItemId() == R.id.bottom_menu_jobs){
                    fragmentTransaction.replace(R.id.main_frame_layout, new JobsFragment()).commit();
                    return true;
                }else if (item.getItemId() == R.id.bottom_menu_search){
                    fragmentTransaction.replace(R.id.main_frame_layout, new SearchFragment()).commit();
                    return true;
                }else if (item.getItemId() == R.id.bottom_menu_message){
                    fragmentTransaction.replace(R.id.main_frame_layout, new ChatFragment()).commit();
                    return true;
                }else if(item.getItemId() == R.id.bottom_menu_account) {
                    fragmentTransaction.replace(R.id.main_frame_layout, new SignInFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}