package com.nitkkr.techspardha.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.nitkkr.techspardha.Interface;
import com.nitkkr.techspardha.Pojo.CatData;
import com.nitkkr.techspardha.Pojo.Data;
import com.nitkkr.techspardha.Pojo.EventCat;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.RetroClient;

import java.util.ArrayList;
import java.util.List;

public class Cat extends AppCompatActivity {

    private List<CatData> edata=new ArrayList<>();
    RecyclerView recyclerView;
    CatAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
         recyclerView = (RecyclerView) findViewById(R.id.cat_recycler);

        LoadJson("Programming");

    }

    public void LoadJson(final String keyword) {


        Interface service = RetroClient.getClient().create(Interface.class);


            service.getCatData(keyword)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CatData>() {

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CatData catData) {

                                    Log.i("Code",catData.getSuccess());
                            edata.add(catData);

                        }


                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                            ArrayList<Data> eventd = new ArrayList<>();

                            for (int i = 0; i < edata.get(0).getData().getEvents().length; i++) {
                                eventd.add(edata.get(0).getData().getEvents()[i]);
                                Log.i("List Size", eventd.get(i).getEventName());


                            }



                            adapter = new CatAdapter(eventd);

                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });


        }





}
