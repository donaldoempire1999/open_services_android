package com.example.openservices.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.databinding.ItemPostPublicationBinding;
import com.example.openservices.models.Publication;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationHolder> {

    private final ArrayList<Publication> publications;
    private final PublicationAdapter.OnItemClickListener itemClickListener;
    private final Context context;

    private LayoutInflater layoutInflater;

    public PublicationAdapter(Context context, ArrayList<Publication> publications, PublicationAdapter.OnItemClickListener itemClickListener) {
        this.context = context;
        this.publications = publications;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public PublicationHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPostPublicationBinding publicationBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_post_publication, parent, false
        );
        return new PublicationHolder(publicationBinding.getRoot(), publicationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PublicationAdapter.PublicationHolder holder, int position) {
        holder.bindPost(position);
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onShareClick(int position);
        void onLikeClick(int position);
        void onUserClick(int position);
    }

    public class PublicationHolder extends RecyclerView.ViewHolder {

        private final ItemPostPublicationBinding dataBiding;

        public PublicationHolder(@NonNull @NotNull View itemView, ItemPostPublicationBinding dataBiding) {
            super(itemView);
            this.dataBiding = dataBiding;
        }

        public void bindPost(int position){
            dataBiding.setPublication(publications.get(position));
            dataBiding.executePendingBindings();
            dataBiding.constraintButtonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position);
                }
            });
            dataBiding.imageButtonFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onLikeClick(position);
                }
            });
            dataBiding.imageButtonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onShareClick(position);
                }
            });
            dataBiding.imageButtonThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onUserClick(position);
                }
            });
            setViews(position);
        }

        private void setViews(int position) {
            //
        }
    }
}
