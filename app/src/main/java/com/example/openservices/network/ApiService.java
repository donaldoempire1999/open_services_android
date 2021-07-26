package com.example.openservices.network;

import com.example.openservices.models.User;
import com.example.openservices.responses.PublicationDetailsResponse;
import com.example.openservices.responses.PublicationResponse;
import com.example.openservices.responses.UserDeleteResponse;
import com.example.openservices.responses.UserDetailsResponse;
import com.example.openservices.responses.UserSignInResponse;
import com.example.openservices.responses.UserSignUpResponse;
import com.example.openservices.responses.UserUpdateResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    //Get all users
    @GET("users")
    Call<ArrayList<User>> getAllUsers();

    //Get user info
    @GET("users/{id}")
    Call<UserDetailsResponse> getUserInfo(@Header("Authorization") String token, @Path("id") String id);

    //Login a user
    @FormUrlEncoded
    @POST("users/login")
    Call<UserSignInResponse> signInUser(@Field("phone_number") String phoneNumber, @Field("mdp") String password);

    //Sign up user
    @POST("users/signUp")
    Call<UserSignUpResponse> signUpUser(@Header("Authorization") User user);

    //Update user
    @PUT("users/{_id}")
    Call<UserUpdateResponse> updateUser(@Path("_id") String userId);

    //Delete user
    @DELETE("users/{_id}")
    Call<UserDeleteResponse> deleteUser(@Path("_id") String userId);


    //Get all posts
    @GET("publications/all")
    Call<PublicationResponse> getAllPublications();

    //Get my posts
    @GET("publications")
    Call<PublicationResponse> getMyPublications();

    //Get post info
    @GET("publications/{id}")
    Call<PublicationDetailsResponse> getPublicationInfo(@Header("Authorization") String token, @Path("id") String id);

    //Search post
}
