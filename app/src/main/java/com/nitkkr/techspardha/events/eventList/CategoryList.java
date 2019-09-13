package com.nitkkr.techspardha.events.eventList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.events.categoryPojo.CategoryData;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class CategoryList extends AppCompatActivity {

    private List<CategoryData> edata=new ArrayList<>();
    RecyclerView recyclerView;
    CategoryListAdapter adapter;
    Bundle bundle;
    String back;
    AVLoadingIndicatorView progress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
         recyclerView = (RecyclerView) findViewById(R.id.cat_recycler);
         progress = findViewById(R.id.category_avi);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("eventList"));

        try {
            back=getIntent().getExtras().getString("eventList");
            LoadJson(getIntent().getExtras().getString("eventList"));
        } catch (Exception e) {
            LoadJson(back);
            e.printStackTrace();
        }

    }

    public void LoadJson(final String keyword) {


        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getCatData(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryData categoryData) {

                        Log.i("Code", categoryData.getSuccess());
                        edata.add(categoryData);

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        progress.setVisibility(View.GONE);
                        ArrayList<Data> eventd = new ArrayList<>();

                        for (int i = 0; i < edata.get(0).getData().getEvents().length; i++) {
                            eventd.add(edata.get(0).getData().getEvents()[i]);
                            Log.i("List Size", eventd.get(i).getEventName());


                        }


                        adapter = new CategoryListAdapter(eventd);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });


        }





}
