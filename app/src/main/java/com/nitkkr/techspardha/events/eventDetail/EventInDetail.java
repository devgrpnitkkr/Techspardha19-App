package com.nitkkr.techspardha.events.eventDetail;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.categoryPojo.EventCategory;

import java.util.ArrayList;
import java.util.List;

public class EventInDetail extends AppCompatActivity {

    private List<Data> edata=new ArrayList<>();
    private Adapter adapter;
    TextView rus;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_in_detail);

        rus=(TextView)findViewById(R.id.description);
        Intent intent=getIntent();
        Data cust=(Data) intent.getSerializableExtra("Obj");

        String desc=cust.getDescription();
        String etime=cust.getEndTime();
        String stime=cust.getStartTime();
        String ename=cust.getEventName();

        String rules="";

        for(int i=0;cust.getRules()!=null && i<cust.getRules().length;i++){
            rules=rules+cust.getRules()[i]+"\n";
        }
        String c1=cust.getCoordinators()[0].getCoordinator_name();
        String c1n=cust.getCoordinators()[0].getCoordinator_number();

        String c2=cust.getCoordinators()[1].getCoordinator_name();
        String c2n=cust.getCoordinators()[1].getCoordinator_number();
        rus.setText(desc);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cust.getEventName());
        collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);

        register=(Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });





    }


    public void Register(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child("007vy8080@gmail,com");




    }






    public void LoadJson(final String keyword,final String ename) {


        Interface service = RetroClient.getClient().create(Interface.class);


        service.getData(keyword,ename)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EventCategory>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EventCategory eventCategory) {


                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        int n=edata.get(0).getRules().length;

                        for(int i=0;i<n;i++){
                            Log.i("Value",edata.get(0).getRules()[i]);
                        }


                    }
                });


    }
}
