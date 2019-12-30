package com.example.notsosecretsanta;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class AppSettings {

    private static AppSettings instance;

    public AppSettings(){

    }

    public static AppSettings getInstance(){
        if (instance == null){
            instance = new AppSettings();
        }
        return instance;
    }

    public void saveListToPreferences(List<String> items, Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.putInt("Status_size", items.size());
        for(int i=0;i<items.size();i++)
        {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, items.get(i));
        }
        mEdit1.commit();
    }

    public List<String> getItemsFromPreferences( Context context){
        List<String> itemsFromp = new ArrayList<>();
        SharedPreferences mSharedPreference1 =   PreferenceManager.getDefaultSharedPreferences(context);
        int size = mSharedPreference1.getInt("Status_size", 0);
        for(int i=0;i<size;i++)
        {
            itemsFromp.add(mSharedPreference1.getString("Status_" + i, null));
        }
        return itemsFromp;
    }

    public void removeSavedItems(Context context){
        List<String> items = new ArrayList<>();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.putInt("Status_size", items.size());
        for(int i=0;i<items.size();i++)
        {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, items.get(i));
        }
        mEdit1.clear();
        mEdit1.commit();
    }


}
