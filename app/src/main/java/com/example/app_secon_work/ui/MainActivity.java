package com.example.app_secon_work.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.app_secon_work.Adapter.ExampleAdapter;
import com.example.app_secon_work.Fragment.SearchFragment;
import com.example.app_secon_work.Fragment.HomeFragment;
import com.example.app_secon_work.Fragment.ProfileFragment;
import com.example.app_secon_work.Model.ExampleItem;
import com.example.app_secon_work.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity   {

    //Llervar los elemntos en la otra activity
   // public static  final String EXTRA_URL ="imageUrl";
    //public static  final String EXTRA_CREATOR ="creatorName";
    //public static  final String EXTRA_LIKES ="likeCount";

    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ExampleItem exampleItem;
    private ArrayList<ExampleItem> exampleItems;
    private RequestQueue requestQueue;

    private static final String TAG = MainActivity.class.getCanonicalName() ;
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        bottomNav = findViewById(R.id.bottom_nav);
//Inicializacion para El fragment De Home
        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.home,true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        }
//Click De distintos Fragment
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.Search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.Profile:
                        fragment = new ProfileFragment();
                        break;

                }
                if (fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();

                }else {
                    Log.e(TAG, "Error in Creating Fragment");
                }
            }
        });
    }





   /* @Override
    public void onItemClick(int position) {
        Intent detalIntent = new Intent(this,DetailActivity.class);
        ExampleItem clicledItem = exampleItems.get(position);

        detalIntent.putExtra(EXTRA_URL, clicledItem.getmImageUrl());
        detalIntent.putExtra(EXTRA_CREATOR, clicledItem.getmCreator());
        detalIntent.putExtra(EXTRA_LIKES, clicledItem.getmLikes());

        startActivity(detalIntent);
    }*/


}