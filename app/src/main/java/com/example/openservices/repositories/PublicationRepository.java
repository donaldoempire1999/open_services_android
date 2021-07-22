package com.example.openservices.repositories;

import android.os.Debug;
import android.util.Log;

import com.example.openservices.models.Publication;
import com.example.openservices.network.ApiClient;
import com.example.openservices.network.ApiService;
import com.example.openservices.responses.PublicationDetailsResponse;
import com.example.openservices.responses.PublicationResponse;
import com.example.openservices.utilities.ConstantValue;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicationRepository {

    private ApiService apiService;

    public PublicationRepository(){
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

    public LiveData<PublicationDetailsResponse> getPublicationInfo(String id) {
        MutableLiveData<PublicationDetailsResponse> data = new MutableLiveData<>();
        apiService.getPublicationInfo(id).enqueue(new Callback<PublicationDetailsResponse>() {
            @Override
            public void onResponse(Call<PublicationDetailsResponse> call, Response<PublicationDetailsResponse> response) {
                Log.e(ConstantValue.TAG, "Good response !");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PublicationDetailsResponse> call, Throwable t) {
                Log.e(ConstantValue.TAG, "Bad response !");
                Log.e(ConstantValue.TAG, "Error : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
