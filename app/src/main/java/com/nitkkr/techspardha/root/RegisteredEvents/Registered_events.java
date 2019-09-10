package com.nitkkr.techspardha.root.RegisteredEvents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.eventList.CategoryListAdapter;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.List;

public class Registered_events extends AppCompatActivity {

    Intent intent;
    private List<Registered> edata=new ArrayList<>();
    RecyclerView recyclerView;
    CategoryListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeres_events);

        recyclerView = (RecyclerView) findViewById(R.id.r_recycler);


        intent = getIntent();
        LoadJson("2000preetamkm@gmail.com");




    }

    public void LoadJson(final String keyword) {

        Log.i("List Size",keyword);


        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getRegisteredEvents(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registered>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registered categoryData) {

                        Log.i("Code", categoryData.getSuccess());
                        edata.add(categoryData);

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        ArrayList<Data> eventd = new ArrayList<>();

                        for (int i = 0; i < edata.get(0).getData().getEvents().length;i++) {
                            eventd.add(edata.get(0).getData().getEvents()[i]);
                            Log.i("List Size Registered", eventd.get(i).getEventName());


                        }


                        adapter = new CategoryListAdapter(eventd);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });


    }
}
