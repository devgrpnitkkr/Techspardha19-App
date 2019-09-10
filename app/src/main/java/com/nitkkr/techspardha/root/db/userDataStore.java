package com.nitkkr.techspardha.root.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nitkkr.techspardha.root.userPojo.Udata;

public class userDataStore {
    private static userDataStore userD;
    private SharedPreferences sharedPreferences;

    public static userDataStore getInstance(Context context) {
        if (userD == null) {
            userD= new userDataStore(context);
        }
        return userD;
    }

    private userDataStore(Context context) {
        sharedPreferences = context.getSharedPreferences("userDataStore",Context.MODE_PRIVATE);
    }

    public void saveData(Udata uData,Boolean onboard ) {


        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(uData);
        prefsEditor.putString("MyObject", json);
        prefsEditor.putBoolean("OnBoard",onboard);
        prefsEditor.commit();
    }

    public void changeState(Boolean onboard){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean("OnBoard",onboard);
        prefsEditor.apply();


    }
    public Boolean getState(){
        Boolean state=sharedPreferences.getBoolean("OnBoard",false);
        return state;


    }


    public Udata getData() {
        Udata udata=new Udata();
        if (sharedPreferences!= null) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("MyObject", "");
            Udata obj = gson.fromJson(json, Udata.class);
            return obj;
        }
        return udata;

    }

}
