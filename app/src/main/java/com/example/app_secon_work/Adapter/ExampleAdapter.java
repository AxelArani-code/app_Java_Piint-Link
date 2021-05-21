package com.example.app_secon_work.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_secon_work.ui.DetailActivity;
import com.example.app_secon_work.Model.ExampleItem;
import com.example.app_secon_work.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    //Llervar los elemntos en la otra activity
    public static  final String EXTRA_URL ="imageUrl";
    public static  final String EXTRA_CREATOR ="creatorName";
    public static  final String EXTRA_CREATOR_TITLE ="creatorTitle";
    public static  final String USER_IMAGE_URL ="userImage";
    public static final String CATEGORIA = "categoria";
    public static  final String EXTRA_LIKES ="likeCount";

    private Context mContext;
    private ArrayList<ExampleItem> mExampleItems;
    private OnItemClickListener mlistener;


    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }

    public ExampleAdapter(Context mContext, ArrayList<ExampleItem> mExampleItems) {
        this.mContext = mContext;
        this.mExampleItems = mExampleItems;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleItems.get(position);
        String imageUrl = currentItem.getmImageUrl();
        String creatorName = currentItem.getmCreator();
        String userImage = currentItem.getUserImage();
        int likeCount = currentItem.getmLikes();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Likes: "+likeCount);
        Picasso.get().load(userImage).fit().centerInside().into(holder.mImageView_User);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalIntent = new Intent(holder.itemView.getContext(),DetailActivity.class);
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

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView, mImageView_User;
        private TextView mTextViewCreator, mTextViewLikes, mTextViewCategoria;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mImageView_User = itemView.findViewById(R.id.image_view_user);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
            mTextViewCategoria = itemView.findViewById(R.id.text_view_creator_detail_indicador);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
