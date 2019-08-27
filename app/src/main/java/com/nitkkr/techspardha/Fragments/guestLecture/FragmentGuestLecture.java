package com.nitkkr.techspardha.Fragments.guestLecture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.CategoryData;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.eventList.CategoryListAdapter;
import com.nitkkr.techspardha.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.techspardha.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FragmentGuestLecture extends Fragment {


    private List<LectureData> ldata = new ArrayList<>();
    List<String> lst = new ArrayList<>();
    LectureData lectureD;
    DiscreteScrollView scrollView;
    TextView name,date,time,desc;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_guest_lecture, container, false);


//        lst.add("https://res.cloudinary.com/demo/image/upload/w_200/lady.jpg");
//        lst.add("https://res.cloudinary.com/demo/image/upload/sample.jpg");
//        lst.add("https://res.cloudinary.com/demo/image/fetch/w_400,f_png/https://upload.wikimedia.org/wikipedia/commons/4/46/Jennifer_Lawrence_at_the_83rd_Academy_Awards.jpg");
//        lst.add("https://res.cloudinary.com/demo/image/upload/w_200/lady.jpg");
//        lst.add("https://res.cloudinary.com/demo/image/upload/c_imagga_crop/family_bench.jpg");
//        lst.add("https://firebasestorage.googleapis.com/v0/b/sports-2f00f.appspot.com/o/images%2F7e65a871-0a64-4fd9-9b22-33a4673bf62f?alt=media&token=d0b9f4a9-6e94-4988-b591-f7048413285b");

        name=view.findViewById(R.id.guest_name);
        date=view.findViewById(R.id.guest_date);
        time=view.findViewById(R.id.guest_time);
        desc=view.findViewById(R.id.guest_description);
        scrollView = view.findViewById(R.id.picker);
        LoadJson();


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void LoadJson() {


        Interface service = RetroClient.getClient("lectures").create(Interface.class);


        service
                .getLectureData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LectureData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LectureData lectureData) {

                        lectureD = lectureData;

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                       Log.i("gestPic",lectureD.getData().getLectures()[0].getImageUrl());
                       Log.i("gestPic",lectureD.getData().getLectures()[1].getImageUrl());
                       Log.i("gestPic",lectureD.getData().getLectures()[2].getImageUrl());
                       Log.i("gestPic",lectureD.getData().getLectures()[3].getImageUrl());
                       Log.i("gestPic",lectureD.getData().getLectures()[4].getImageUrl());

                       lst.add(lectureD.getData().getLectures()[0].getImageUrl());
                       lst.add(lectureD.getData().getLectures()[1].getImageUrl());
                       lst.add(lectureD.getData().getLectures()[2].getImageUrl());
                       lst.add(lectureD.getData().getLectures()[3].getImageUrl());
                       lst.add(lectureD.getData().getLectures()[4].getImageUrl());

                       List<Lectures> lst=new ArrayList<>();

                       for(int i=0;i<lectureD.getData().getLectures().length;i++){
                           lst.add(lectureD.getData().getLectures()[i]);
                       }

                       scrollView.setAdapter(new FragmentGuestLectureAdapter(lst,name,date,time,desc));
                    }
                });


    }


}
