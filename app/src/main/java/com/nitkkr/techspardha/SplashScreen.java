package com.nitkkr.techspardha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.nitkkr.techspardha.Fragments.MainActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT= 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
                if (!sp.getBoolean("first", false)) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("first", true);
                    editor.apply();
                    Intent intent = new Intent(SplashScreen.this, MainScreen.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent1 = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent1);
                    finish();
                
                }

            }
        },SPLASH_TIME_OUT);

    }
}
