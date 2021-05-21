package com.example.app_secon_work.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.synnapps.carouselview.CarouselView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment  {



    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ArrayList<ExampleItem> exampleItems;
    private RequestQueue requestQueue;


    CarouselView carouselView;

    ShimmerFrameLayout layout;
    ImageView mImageView;
    TextView mTextViewCreator, mTextViewLike;
    Handler handler = new Handler();



    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
//Carousel
        /*
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);*/



        //RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        exampleItems = new ArrayList<>();


        requestQueue = Volley.newRequestQueue(getContext());
        parseJSON();

        return  view;
    }
    //Carousel
    /*ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };*/


    //RecyclerView
    private void parseJSON() {
        //https://pixabay.com/api/?key=20558515-58adf235a449fc902ba4ffe35&q=yellow+flowers&image_type=photo&pretty=true
        //https://run.mocky.io/v3/57c06fb5-2e80-43b8-9660-444814e0c8c6
        String url = "https://run.mocky.io/v3/2557220e-4151-4040-8224-07950595f71f";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

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
                            exampleAdapter = new ExampleAdapter(getContext(),exampleItems);
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