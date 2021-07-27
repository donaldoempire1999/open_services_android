package com.example.openservices;

import android.os.Bundle;

import com.example.openservices.ui.MainFragment;
import com.example.openservices.utilities.ConstantValue;
import com.example.openservices.utilities.SharedPreferencesManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPreferencesManager.getUserInfo(this).get(1) != null) {
            ConstantValue.setTOKEN(SharedPreferencesManager.getUserInfo(this).get(1));
        }else{
            ConstantValue.setTOKEN("");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}