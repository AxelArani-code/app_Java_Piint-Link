package com.example.app_secon_work.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_secon_work.Adapter.ExampleAdapter;
import com.example.app_secon_work.Adapter.ItmsAdapter;
import com.example.app_secon_work.Model.ExampleItem;
import com.example.app_secon_work.R;
import com.example.app_secon_work.ui.View.ViewActivity;
import com.example.app_secon_work.ui.View.ViewBeatle;
import com.example.app_secon_work.ui.View.ViewDising;
import com.example.app_secon_work.ui.View.ViewEscritorio;
import com.example.app_secon_work.ui.View.ViewEstilo;
import com.example.app_secon_work.ui.View.ViewGrunge;
import com.example.app_secon_work.ui.View.ViewMotos;
import com.example.app_secon_work.ui.View.View_Dubujo;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements View.OnClickListener{

private RoundedImageView bt_wallpaper, bt_Escritorio, bt_Estilo, bt_Motos,bt_Dibujos,bt_Diseños,bt_Fotografias,bt_The_Beatles;

    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ItmsAdapter itmsAdapter;
    private ArrayList<ExampleItem> exampleItems = new ArrayList<>();
    private RequestQueue requestQueue;
    public SearchFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);



        bt_wallpaper = view.findViewById(R.id.bt_wallpaper);
        bt_Escritorio = view.findViewById(R.id.bt_Escritorio);
        bt_Estilo = view.findViewById(R.id.bt_Estilo);
        bt_Motos = view.findViewById(R.id.bt_Motos);


//Button
        bt_wallpaper.setOnClickListener(this);
        bt_Escritorio.setOnClickListener(this);
        bt_Estilo.setOnClickListener(this);
        bt_Motos.setOnClickListener(this);



        recyclerView = view.findViewById(R.id.recycler_View_See_More);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        exampleItems = new ArrayList<>();


        requestQueue = Volley.newRequestQueue(getContext());
        parseJSON();
        return view;


    }



    //RecyclerView
    private void parseJSON() {
        //https://pixabay.com/api/?key=20558515-58adf235a449fc902ba4ffe35&q=yellow+flowers&image_type=photo&pretty=true
        //https://run.mocky.io/v3/57c06fb5-2e80-43b8-9660-444814e0c8c6
        String url = "https://run.mocky.io/v3/ce7929d5-5abd-448a-8e85-69c319b70157";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits_see");

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
                            itmsAdapter = new ItmsAdapter(getContext(),exampleItems);
                            recyclerView.setAdapter(itmsAdapter);
                            recyclerView.setPadding(20,0,220,0);

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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_wallpaper: intent = new Intent(getContext(),ViewActivity.class); startActivity(intent); break;
            case R.id.bt_Escritorio: intent = new Intent(getContext(), ViewEscritorio.class); startActivity(intent); break;
            case R.id.bt_Estilo: intent = new Intent(getContext(),ViewEstilo.class); startActivity(intent); break;
            case R.id.bt_Motos: intent = new Intent(getContext(), ViewMotos.class); startActivity(intent); break;


        }

    }

}