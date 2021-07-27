package com.example.openservices.repositories;

import android.util.Log;

import com.example.openservices.models.Period;
import com.example.openservices.models.Publication;
import com.example.openservices.models.SearchObjectSend;
import com.example.openservices.models.TaskDescription;
import com.example.openservices.network.ApiClient;
import com.example.openservices.network.ApiService;
import com.example.openservices.responses.APIError;
import com.example.openservices.responses.PublicationDetailsResponse;
import com.example.openservices.responses.PublicationResponse;
import com.example.openservices.responses.PublicationSaveResponse;
import com.example.openservices.utilities.ConstantValue;
import com.google.gson.Gson;

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

    public LiveData<PublicationDetailsResponse> getPublicationInfo(String id) {
        MutableLiveData<PublicationDetailsResponse> data = new MutableLiveData<>();
        apiService.getPublicationInfo(id).enqueue(new Callback<PublicationDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<PublicationDetailsResponse> call, @NotNull Response<PublicationDetailsResponse> response) {
                if(response.isSuccessful()) {
                    Log.e(ConstantValue.TAG, "API Response : " + response.message());
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                    Log.e(ConstantValue.TAG, "API Response : " + response.message());
                    Log.e(ConstantValue.TAG, String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<PublicationDetailsResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "API Response : " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<ArrayList<Publication>> searchPublications(String type, String collection, String query) {
        MutableLiveData<ArrayList<Publication>> data = new MutableLiveData<>();
        SearchObjectSend searchObjectSend = new SearchObjectSend();
        searchObjectSend.setCollection(collection);
        searchObjectSend.setQuery_string(query);
        apiService.searchPublications(type, searchObjectSend).enqueue(new Callback<ArrayList<Publication>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Publication>> call, @NotNull Response<ArrayList<Publication>> response) {
                Log.e(ConstantValue.TAG, "API response : " +response.message());
                Gson gson = new Gson();
                if (response.errorBody() != null) {
                    APIError apiError = gson.fromJson(response.errorBody().charStream(), APIError.class);
                    Log.e(ConstantValue.TAG, "API error : " +apiError.getError());
                }
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Publication>> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "API response : " +t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<PublicationSaveResponse> savePublication(Publication publication) {
        MutableLiveData<PublicationSaveResponse> data = new MutableLiveData<>();
        apiService.savePublication(publication).enqueue(new Callback<PublicationSaveResponse>() {
            @Override
            public void onResponse(@NotNull Call<PublicationSaveResponse> call, @NotNull Response<PublicationSaveResponse> response) {
                Log.e(ConstantValue.TAG, "API response : " +response.message());
                Gson gson = new Gson();
                if (response.errorBody() != null) {
                    APIError apiError = gson.fromJson(response.errorBody().charStream(), APIError.class);
                    Log.e(ConstantValue.TAG, "API error : " +apiError.getError());
                }
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<PublicationSaveResponse> call, @NotNull Throwable t) {
                Log.e(ConstantValue.TAG, "API response : " +t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
