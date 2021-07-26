package com.example.openservices.repositories;

import android.util.Log;

import com.example.openservices.models.Publication;
import com.example.openservices.models.PublicationDetail;
import com.example.openservices.models.User;
import com.example.openservices.network.ApiClient;
import com.example.openservices.network.ApiService;
import com.example.openservices.responses.PublicationDetailsResponse;
import com.example.openservices.responses.PublicationResponse;
import com.example.openservices.utilities.ConstantValue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicationRepository {

    private ApiService apiService;

    public PublicationRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<PublicationResponse> getAllPublications() {
        MutableLiveData<PublicationResponse> data = new MutableLiveData<>();
        apiService.getAllPublications().enqueue(new Callback<PublicationResponse>() {
            @Override
            public void onResponse(@NotNull Call<PublicationResponse> call, @NotNull Response<PublicationResponse> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<PublicationResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<PublicationResponse> getMyPublications() {
        MutableLiveData<PublicationResponse> data = new MutableLiveData<>();
        apiService.getMyPublications().enqueue(new Callback<PublicationResponse>() {
            @Override
            public void onResponse(@NotNull Call<PublicationResponse> call, @NotNull Response<PublicationResponse> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<PublicationResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<PublicationDetailsResponse> getPublicationInfo(String token, String id) {
        MutableLiveData<PublicationDetailsResponse> data = new MutableLiveData<>();
        apiService.getPublicationInfo(token, id).enqueue(new Callback<PublicationDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<PublicationDetailsResponse> call, @NotNull Response<PublicationDetailsResponse> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                if (response.body() != null) {
                    if (response.body().getPublications() != null){
                        Log.e(ConstantValue.TAG, "API Response : " +response.body().getPublications().size());
                    }
                }
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<PublicationDetailsResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<ArrayList<Publication>> searchPublications(String type, String collection, String query) {
        MutableLiveData<ArrayList<Publication>> data = new MutableLiveData<>();
        apiService.searchPublications(type, collection, query).enqueue(new Callback<ArrayList<Publication>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Publication>> call, @NotNull Response<ArrayList<Publication>> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Publication>> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

}
