package com.example.app_secon_work.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_secon_work.Model.ExampleItem;
import com.example.app_secon_work.R;
import com.example.app_secon_work.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class ItmsAdapter extends RecyclerView.Adapter<ItmsAdapter.ItmsViewHolder> {


    //Llervar los elemntos en la otra activity
    public static  final String EXTRA_URL ="imageUrl";
    public static  final String EXTRA_CREATOR ="creatorName";
    public static  final String EXTRA_CREATOR_TITLE ="creatorTitle";
    public static  final String USER_IMAGE_URL ="userImage";
    public static final String CATEGORIA = "categoria";
    public static  final String EXTRA_LIKES ="likeCount";

    private Context mContext;
    private ArrayList<ExampleItem> mExampleItems;

    public ItmsAdapter(Context mContext, ArrayList<ExampleItem> mExampleItems) {
        this.mContext = mContext;
        this.mExampleItems = mExampleItems;
    }

    @NonNull
    @Override
    public ItmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_itms,parent,false);
        return new ItmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItmsViewHolder holder, int position) {
        ExampleItem currentItem = mExampleItems.get(position);
        String imageUrl = currentItem.getmImageUrl();

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.imageSeeMore);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalIntent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                ExampleItem clicledItem = mExampleItems.get(position);

                detalIntent.putExtra(EXTRA_URL, clicledItem.getmImageUrl());
                detalIntent.putExtra(USER_IMAGE_URL,clicledItem.getUserImage());
                detalIntent.putExtra(EXTRA_CREATOR, clicledItem.getmCreator());
                detalIntent.putExtra(EXTRA_CREATOR_TITLE,clicledItem.getmTitleCreator());
                detalIntent.putExtra(CATEGORIA,clicledItem.getCategoria());
                detalIntent.putExtra(EXTRA_LIKES, clicledItem.getmLikes());

                holder.itemView.getContext().startActivity(detalIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mExampleItems.size();
    }

    public class ItmsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageSeeMore;
        private TextView mTextViewCreator, mTextViewLikes, mTextViewCategoria;

        public ItmsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSeeMore =  itemView.findViewById(R.id.See_More);
        }
    }
}
