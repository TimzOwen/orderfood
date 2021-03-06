package com.codewithtimzowen.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.codewithtimzowen.orderfood.adapter.PopularAdapter;
import com.codewithtimzowen.orderfood.adapter.RecommendedAdpater;
import com.codewithtimzowen.orderfood.model.FoodData;
import com.codewithtimzowen.orderfood.model.Popular;
import com.codewithtimzowen.orderfood.model.Recommended;
import com.codewithtimzowen.orderfood.retrofit.ApiInterface;
import com.codewithtimzowen.orderfood.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    PopularAdapter popularAdapter;
    RecommendedAdpater recommendedAdpater;

    RecyclerView popularRecyclerView, recommendedRecyclerView, PopularRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodData = response.body();

                getPopularData(foodData.get(0).getPopular());

                getRecommendedData(foodData.get(0).getRecommended());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Server not responding..", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //load data
    private void getPopularData (List < Popular > popularList) {

        popularRecyclerView = findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

    private void getRecommendedData (List <Recommended> recommendedList) {

        recommendedRecyclerView = findViewById(R.id.recommended_recycler);
        recommendedAdpater = new RecommendedAdpater(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdpater);

    }

    //updated the paymenys for Paypal from M-pesa

}