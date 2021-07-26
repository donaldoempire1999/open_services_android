package com.example.openservices.repositories;

import android.util.Log;

import com.example.openservices.models.User;
import com.example.openservices.network.ApiClient;
import com.example.openservices.network.ApiService;
import com.example.openservices.responses.UserDetailsResponse;
import com.example.openservices.responses.UserResponse;
import com.example.openservices.responses.UserSignInResponse;
import com.example.openservices.responses.UserSignUpResponse;
import com.example.openservices.utilities.ConstantValue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService apiService;

    public UserRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<ArrayList<User>> getAllUsers() {
        MutableLiveData<ArrayList<User>> data = new MutableLiveData<>();
        apiService.getAllUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<User>> call, @NotNull Response<ArrayList<User>> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<User>> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<UserDetailsResponse> getUserInfo(String token, String id) {
        MutableLiveData<UserDetailsResponse> data = new MutableLiveData<>();
        apiService.getUserInfo("Bearer " +token, id).enqueue(new Callback<UserDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserDetailsResponse> call, @NotNull Response<UserDetailsResponse> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<UserDetailsResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<UserSignInResponse> signIn(String phoneNumber, String password) {
        MutableLiveData<UserSignInResponse> data = new MutableLiveData<>();
        apiService.signInUser(phoneNumber, password).enqueue(new Callback<UserSignInResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserSignInResponse> call, @NotNull Response<UserSignInResponse> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<UserSignInResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<UserSignUpResponse> signUp(User user) {
        MutableLiveData<UserSignUpResponse> data = new MutableLiveData<>();
        apiService.signUpUser(user).enqueue(new Callback<UserSignUpResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserSignUpResponse> call, @NotNull Response<UserSignUpResponse> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<UserSignUpResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(new UserSignUpResponse());
            }
        });
        return data;
    }
}
