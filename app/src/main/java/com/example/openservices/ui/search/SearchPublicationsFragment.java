package com.example.openservices.ui.search;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.openservices.R;
import com.example.openservices.responses.PublicationResponse;
import com.example.openservices.viewmodels.PublicationViewModel;

public class SearchPublicationsFragment extends Fragment {

    private PublicationViewModel publicationViewModel;
    private FragmentActivity activity;
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_publications, container, false);
        context = getContext();
        activity = getActivity();
        if (activity != null)
            publicationViewModel = new ViewModelProvider(getActivity()).get(PublicationViewModel.class);

        if (activity != null)
            getPublications();
        return view;
    }

    private void getPublications() {
        publicationViewModel.getAllPublications().observe(activity, new Observer<PublicationResponse>() {
            @Override
            public void onChanged(PublicationResponse publicationResponse) {
                if (publicationResponse != null){
                    if (publicationResponse.getPublications() != null){
                        Toast.makeText(activity, "Total pages : " +publicationResponse.getPublications().size(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}