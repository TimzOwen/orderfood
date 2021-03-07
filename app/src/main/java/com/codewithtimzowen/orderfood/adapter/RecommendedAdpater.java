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
import com.codewithtimzowen.orderfood.model.Recommended;

import java.util.List;

public class RecommendedAdpater extends RecyclerView.Adapter<RecommendedAdpater.RecommendedViewHolder> {

    private Context context;
    private List<Recommended> recommendedList;

    public RecommendedAdpater(Context context, List<Recommended> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popula_recycler_items,parent,false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {

        holder.recommendedName.setText(recommendedList.get(position).getName());
        holder.recommendedRating.setText(recommendedList.get(position).getRating());
        holder.recommendedCharges.setText(recommendedList.get(position).getDeliveryCharges());
        holder.recommendedDeliveryTime.setText(recommendedList.get(position).getDeliveryTime());
        holder.getRecommendedPrice.setText("Ksh:" + recommendedList.get(position).getPrice());

        Glide.with(context).load(recommendedList.get(position).getImageUrl()).into(holder.recommendedImage);

        //set onClick Listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodDetails = new Intent(context, FoodDetails.class);
                foodDetails.putExtra("name",recommendedList.get(position).getName());
                foodDetails.putExtra("price",recommendedList.get(position).getPrice());
                foodDetails.putExtra("image",recommendedList.get(position).getImageUrl());

                context.startActivity(foodDetails); // start the activity from the current context
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public static class RecommendedViewHolder extends RecyclerView.ViewHolder{

        ImageView recommendedImage;
        TextView recommendedName, recommendedRating,recommendedDeliveryTime, recommendedCharges, getRecommendedPrice;

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendedImage = itemView.findViewById(R.id.recommended_image);
            recommendedName = itemView.findViewById(R.id.recommended_name);
            recommendedDeliveryTime = itemView.findViewById(R.id.time_delivery);
            recommendedCharges = itemView.findViewById(R.id.delivery_type);
            recommendedRating = itemView.findViewById(R.id.recommended_rating);
            getRecommendedPrice = itemView.findViewById(R.id.recommended_price);


        }
    }
}
