package com.example.openservices.network;

import com.example.openservices.models.Publication;
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
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjBmNTk5NWI2ZGZmNDUyYWMwMzdlNmZmIiwiaWF0IjoxNjI3MjQwODUzLCJleHAiOjE2MjczMjcyNTN9.-HfLDn_VIdgrPkWDfqeEvJyuYO7wRQ9Ijj3vHIv_wmw")
    @GET("publications/{id_pub}")
    Call<PublicationDetailsResponse> getPublicationInfo(@Header("Authorization") String token, @Path("id_pub") String id_pub);

    //Search post
    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("search/{type_search}")
    Call<ArrayList<Publication>> searchPublications(@Path("type_search") String type_search, @Field("collection") String collection, @Field("query_string") String query_string);

    //Search user
    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("search/{type_search}")
    Call<ArrayList<User>> searchUsers(@Path("type_search") String type_search, @Field("collection") String collection, @Field("query_string") String query_string);
}
