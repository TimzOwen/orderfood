package com.codewithtimzowen.orderfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codewithtimzowen.orderfood.FoodDetails;
import com.codewithtimzowen.orderfood.R;
import com.codewithtimzowen.orderfood.model.Allmenu;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

    Context context;
    List<Allmenu> allmenuList;

    public AllMenuAdapter(Context context, List<Allmenu> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items,parent,false);

        return new AllMenuViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, int position) {
        holder.popularName.setText(allmenuList.get(position).getName());
        holder.popularPrice.setText(allmenuList.get(position).getPrice());
        holder.popularTime.setText(allmenuList.get(position).getDeliveryTime());
        holder.popularRating.setText(allmenuList.get(position).getRating());
        holder.popularCharges.setText(allmenuList.get(position).getDeliveryCharges());
        holder.popularNote.setText(allmenuList.get(position).getNote());

        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.popularImageView);

        //set onClick Listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodDetails = new Intent(context, FoodDetails.class);
                foodDetails.putExtra("name",allmenuList.get(position).getName());
                foodDetails.putExtra("price",allmenuList.get(position).getPrice());
                foodDetails.putExtra("rating",allmenuList.get(position).getRating());
                foodDetails.putExtra("image",allmenuList.get(position).getImageUrl());

                context.startActivity(foodDetails); // start the activity from the current context
            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView popularName, popularNote, popularRating,popularTime,popularCharges,popularPrice;
        ImageView popularImageView;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            popularName = itemView.findViewById(R.id.popular_name);
            popularNote = itemView.findViewById(R.id.popular_note);
            popularRating = itemView.findViewById(R.id.popular_rating);
            popularCharges = itemView.findViewById(R.id.popular_delivery_charge);
            popularTime = itemView.findViewById(R.id.popular_deliveryTime);
            popularPrice = itemView.findViewById(R.id.popular_price);
            popularImageView = itemView.findViewById(R.id.popular_image);

        }
    }

    }
