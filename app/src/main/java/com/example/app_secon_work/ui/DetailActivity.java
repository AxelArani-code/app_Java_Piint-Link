package com.example.app_secon_work.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_secon_work.Adapter.ExampleAdapter;
import com.example.app_secon_work.Model.ExampleItem;
import com.example.app_secon_work.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.util.ArrayList;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

import static com.example.app_secon_work.Adapter.ExampleAdapter.CATEGORIA;
import static com.example.app_secon_work.Adapter.ExampleAdapter.EXTRA_CREATOR;
import static com.example.app_secon_work.Adapter.ExampleAdapter.EXTRA_CREATOR_TITLE;
import static com.example.app_secon_work.Adapter.ExampleAdapter.EXTRA_LIKES;
import static com.example.app_secon_work.Adapter.ExampleAdapter.EXTRA_URL;
import static com.example.app_secon_work.Adapter.ExampleAdapter.USER_IMAGE_URL;


public class DetailActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextViewCreator, mTextViewLikes;
    private Button button_save, getButton_back, bt_Share;
    private ArrayList<ExampleItem> mExampleItems;
    private OutputStream outputStream;



    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ArrayList<ExampleItem> exampleItems = new ArrayList<>();
    private RequestQueue requestQueue;
    private ImageViewZoom imageViewZoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        String creatorTitle = intent.getStringExtra(EXTRA_CREATOR_TITLE);
        String  userImage = intent.getStringExtra(USER_IMAGE_URL);
        String categoria = intent.getStringExtra(CATEGORIA);
        int likeCout = intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView= findViewById(R.id.image_view_detail);
        ImageView imageView_user= findViewById(R.id.image_view_detail_user);
        TextView textViewCreator = findViewById(R.id.text_view_creator_detail);
        TextView textViewTitle = findViewById(R.id.text_view_creator_title);
        TextView textViewCategoria = findViewById(R.id.text_view_creator_detail_indicador);
        TextView textViewLikes = findViewById(R.id.text_view_like_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        Picasso.get().load(userImage).fit().centerInside().into(imageView_user);
        textViewCreator.setText(creatorName);
        textViewTitle.setText(creatorTitle);
        textViewCategoria.setText(categoria);
        textViewLikes.setText("Likes:"+ likeCout);





        ImageView imagenView = findViewById(R.id.image_view_detail);


        bt_Share = findViewById(R.id.bt_Share);
        bt_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Share = new Intent(Intent.ACTION_SEND);
                intent_Share.setType("text/plain");
                String Body = "Descargar la App";
                String Sub = "https://play.google.com/store?hl=es_AR&gl=US";
                intent_Share.putExtra(Intent.EXTRA_TEXT,Body);
                intent_Share.putExtra(Intent.EXTRA_TEXT,Sub);
                startActivity(Intent.createChooser(intent_Share,"Compartir"));
            }
        });


        //Button Back in MainActivity
        getButton_back = findViewById(R.id.Button_Back);
        getButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.recycler_view_detalles);

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
                            JSONArray jsonArray = response.getJSONArray("hits_1");

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
                            exampleAdapter = new ExampleAdapter(DetailActivity.this,exampleItems);
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