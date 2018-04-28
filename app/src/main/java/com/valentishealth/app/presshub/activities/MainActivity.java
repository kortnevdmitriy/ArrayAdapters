package com.valentishealth.app.presshub.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.valentishealth.app.presshub.R;
import com.valentishealth.app.presshub.adapters.ModelAdapter;
import com.valentishealth.app.presshub.api.Api;
import com.valentishealth.app.presshub.model.Modelist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private final static String BASE_URL = "https://gist.githubusercontent.com";
    private List<Modelist> modelists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonRequest();

    }

    // This methods fetches the data from the endpoint
    private void jsonRequest() {
        // Instantiating and Initializing Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiEndpoint = retrofit.create(Api.class);

        // Set up the call to the API
        Call<List<Modelist>> call = apiEndpoint.loadData();

        // Runs the call on a different thread to avoid Networking on UI Thread
        call.enqueue(new Callback<List<Modelist>>() {
            @Override
            public void onResponse(Call<List<Modelist>> call, Response<List<Modelist>> response) {
                if (response.isSuccessful()) {
                    modelists = response.body();
                    if (modelists != null) {
                        displayData();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Strange error occured", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Modelist>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    // This method displays the fetched data
    private void displayData() {
        // Create a RecyclerView & find it's view by id to populate it with data
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ModelAdapter adapter = new ModelAdapter(getApplicationContext(), modelists);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
