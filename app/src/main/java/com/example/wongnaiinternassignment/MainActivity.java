package com.example.wongnaiinternassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wongnaiinternassignment.Models.Coin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Coin> mCoin;
    private static String JSON_URL = "https://api.coinranking.com/v1/public/coins";
    private Adapter mAdapter;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager((new LinearLayoutManager(this)));

        mCoin = new ArrayList<>();
        
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }


    private void parseJSON() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONObject("data").getJSONArray("coins");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String name = hit.getString("name");
                                String description = hit.getString("description");
                                String iconUrl = hit.getString("iconUrl");

                                mCoin.add(new Coin(name, description, iconUrl));
                            }
                            mAdapter = new Adapter(MainActivity.this, mCoin);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}




