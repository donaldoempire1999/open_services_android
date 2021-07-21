package com.example.openservices.network;

import com.example.openservices.models.User;
import com.example.openservices.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    //Get all users
    @GET("users")
    Call<UserResponse> listUsers();

    //Get specific user
    @GET("users/{_id}")
    Call<UserResponse> getBookInfo(@Path("_id") int userId);

    //Login a user
    @POST("users/login")
    Call<UserResponse> addBook(@Body User user);

}
