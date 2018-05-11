package com.example.vanphu.mymoney.model;



public class MoneyModel {
    private int mImage;
    private String mUser;
    private int mMoney;
    private String mKeymoney;

    public MoneyModel(int mImage, String mKeymoney) {
        this.mImage = mImage;
        this.mKeymoney = mKeymoney;
    }

    public MoneyModel(int mImage, String mUser, int mMoney, String mKeymoney) {
        this.mImage = mImage;
        this.mUser = mUser;
        this.mMoney = mMoney;
        this.mKeymoney = mKeymoney;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmUser() {
        return mUser;
    }

    public int getmMoney() {
        return mMoney;
    }

    public String getmKeymoney() {
        return mKeymoney;
    }
}
