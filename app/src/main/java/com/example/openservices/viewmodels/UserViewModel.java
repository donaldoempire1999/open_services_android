package com.example.openservices.viewmodels;

import com.example.openservices.models.User;
import com.example.openservices.repositories.UserRepository;
import com.example.openservices.responses.UserDetailsResponse;
import com.example.openservices.responses.UserResponse;
import com.example.openservices.responses.UserSignInResponse;
import com.example.openservices.responses.UserSignUpResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private UserRepository repository;

    public UserViewModel() {
        repository = new UserRepository();
    }

    public LiveData<UserResponse> getAllUsers(){
        return repository.getAllUsers();
    }
    public LiveData<UserDetailsResponse> getUserInfo(String id){
        return repository.getUserInfo(id);
    }
    public LiveData<UserSignInResponse> signIn(String phoneNumber, String password){
        return repository.signIn(phoneNumber, password);
    }
    public LiveData<UserSignUpResponse> signUp(User user){
        return repository.signUp(user);
    }
}
