package com.example.openservices.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.models.Publication;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationHolder> {

    private ArrayList<Publication> publications;
    private LayoutInflater layoutInflater;




    @NonNull
    @NotNull
    @Override
    public PublicationHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PublicationAdapter.PublicationHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public class PublicationHolder extends RecyclerView.ViewHolder{

        public PublicationHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
