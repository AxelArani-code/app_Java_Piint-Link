package com.example.app_secon_work.Model;

public class ExampleItem {
    private String mImageUrl;
    private String mTitleCreator;
    private  String mCreator;
    private String userImage;
    private String categoria;
    private int mLikes;

    public ExampleItem() {
    }

    public ExampleItem(String mImageUrl, String mTitleCreator, String mCreator, String userImage, String categoria, int mLikes) {
        this.mImageUrl = mImageUrl;
        this.mTitleCreator = mTitleCreator;
        this.mCreator = mCreator;
        this.userImage = userImage;
        this.categoria = categoria;
        this.mLikes = mLikes;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmTitleCreator() {
        return mTitleCreator;
    }

    public void setmTitleCreator(String mTitleCreator) {
        this.mTitleCreator = mTitleCreator;
    }

    public String getmCreator() {
        return mCreator;
    }

    public void setmCreator(String mCreator) {
        this.mCreator = mCreator;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getmLikes() {
        return mLikes;
    }

    public void setmLikes(int mLikes) {
        this.mLikes = mLikes;
    }
}

