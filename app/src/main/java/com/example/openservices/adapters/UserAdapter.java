package com.example.openservices.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openservices.R;
import com.example.openservices.databinding.ItemPostUserBinding;
import com.example.openservices.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private ArrayList<User> users;
    private UserAdapter.OnItemClickListener itemClickListener;
    private Context context;

    private LayoutInflater layoutInflater;

    public UserAdapter(Context context, ArrayList<User> users, UserAdapter.OnItemClickListener itemClickListener) {
        this.users = users;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPostUserBinding postUserBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_post_user, parent, false
        );
        return new UserHolder(postUserBinding.getRoot(), postUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.UserHolder holder, int position) {
        holder.bindUser(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onShareClick(int position);
        void onUserClick(int position);
    }

    public class UserHolder extends RecyclerView.ViewHolder{
        private ItemPostUserBinding dataBiding;

        public UserHolder(@NonNull @NotNull View itemView, ItemPostUserBinding dataBiding) {
            super(itemView);
            this.dataBiding = dataBiding;
        }

        public void bindUser(int position){
            dataBiding.setUser(users.get(position));
            dataBiding.executePendingBindings();
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
            dataBiding.constraintButtonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position);
                }
            });
        }
    }
}
