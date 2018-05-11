package com.example.vanphu.mymoney.Model;

/**
 * Created by VanPhu on 5/8/2018.
 */

public class SpendModel {
    private int mId;
    private int mImage;
    private String mTilte;
    private int mPrice;
    private String mUser;
    private String mDate;

    public SpendModel(int mId, int mImage, String mTilte, int mPrice, String mUser, String mDate) {
        this.mId = mId;
        this.mImage = mImage;
        this.mTilte = mTilte;
        this.mPrice = mPrice;
        this.mUser = mUser;
        this.mDate = mDate;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmTilte() {
        return mTilte;
    }

    public void setmTilte(String mTilte) {
        this.mTilte = mTilte;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}




