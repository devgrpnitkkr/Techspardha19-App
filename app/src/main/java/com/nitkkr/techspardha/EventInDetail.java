package com.nitkkr.techspardha;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.nitkkr.techspardha.Pojo.Data;
import com.nitkkr.techspardha.Pojo.EventCat;

import java.util.ArrayList;
import java.util.List;

public class EventInDetail extends AppCompatActivity {

    private List<Data> edata=new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_in_detail);
    }
    public void LoadJson(final String keyword,final String ename) {


        Interface service = RetroClient.getClient().create(Interface.class);


        service.getData(keyword,ename)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EventCat>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EventCat eventCat) {


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
