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
import com.codewithtimzowen.orderfood.model.Popular;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {
    private Context context;
    private List<Popular> popularList;

    public PopularAdapter(Context context, List<Popular> popularList) {
        this.context = context;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popula_recycler_items,parent,false);



        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.popularName.setText(popularList.get(position).getName());

        //load image now with glide  make sure to have all dependencies
        Glide.with(context).load(popularList.get(position).getImageUrl()).into(holder.popularImage);

        //set onClick Listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodDetails = new Intent(context, FoodDetails.class);
                foodDetails.putExtra("name",popularList.get(position).getName());
                foodDetails.putExtra("price",popularList.get(position).getPrice());
                foodDetails.putExtra("rating",popularList.get(position).getRating());
                foodDetails.putExtra("image",popularList.get(position).getImageUrl());

                context.startActivity(foodDetails); // start the activity from the current context
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder{

        ImageView popularImage;
        TextView popularName;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            popularName = itemView.findViewById(R.id.popular_name);
            popularImage = itemView.findViewById(R.id.popular_image);
        }
    }
}
