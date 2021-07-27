package com.example.openservices.viewmodels;

import com.example.openservices.models.Publication;
import com.example.openservices.repositories.PublicationRepository;
import com.example.openservices.responses.PublicationDetailsResponse;
import com.example.openservices.responses.PublicationResponse;
import com.example.openservices.responses.PublicationSaveResponse;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class PublicationViewModel extends ViewModel {

    private PublicationRepository repository;

    public PublicationViewModel() {
        repository = new PublicationRepository();
    }

    public LiveData<PublicationResponse> getAllPublications() {
        return repository.getAllPublications();
    }

    public LiveData<PublicationResponse> getMyPublications() {
        return repository.getMyPublications();
    }

    public LiveData<PublicationDetailsResponse> getPublicationInfo(String id) {
        return repository.getPublicationInfo(id);
    }

    public LiveData<ArrayList<Publication>> searchPublications(String type, String collection, String query) {
        return repository.searchPublications(type, collection, query);
    }

    public LiveData<PublicationSaveResponse> savePublication(Publication publication) {
        return repository.savePublication(publication);
    }
}
