package com.example.app_secon_work.ui.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_secon_work.Adapter.ExampleAdapter;
import com.example.app_secon_work.Model.ExampleItem;
import com.example.app_secon_work.R;
import com.example.app_secon_work.ui.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewGrunge extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ArrayList<ExampleItem> exampleItems;
    private RequestQueue requestQueue;
    private Button Button_Back_View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_grunge);


        Button_Back_View = findViewById(R.id.Button_Back_View);
        Button_Back_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        recyclerView = findViewById(R.id.recycler_View_Grunge);

        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        exampleItems = new ArrayList<>();


        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        //https://pixabay.com/api/?key=20558515-58adf235a449fc902ba4ffe35&q=yellow+flowers&image_type=photo&pretty=true
        //https://run.mocky.io/v3/49b209ac-a7de-45d5-aa82-4a3ad1da2dac
        String url = "https://run.mocky.io/v3/2557220e-4151-4040-8224-07950595f71f";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits_dibujos_psicologicos");

                            for (int i = 0; i< jsonArray.length(); i ++){
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("user");
                                String creatorTitle = hit.getString("user_name");
                                String userImage = hit.getString("userImageURL");
                                String imageUrl = hit.getString("webformatURL");
                                String categoria = hit.getString("categoria");
                                int likeCount = hit.getInt("likes");

                                exampleItems.add(new ExampleItem(imageUrl, creatorName,creatorTitle,userImage,categoria, likeCount));
                            }
                            //Adapter
                            exampleAdapter = new ExampleAdapter(ViewGrunge.this,exampleItems);
                            recyclerView.setAdapter(exampleAdapter);

                            // exampleAdapter.setOnItemClickListener(getContext(),);

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
        requestQueue.add(request);
    }
}