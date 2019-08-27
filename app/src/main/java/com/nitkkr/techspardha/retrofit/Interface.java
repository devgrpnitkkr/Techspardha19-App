package com.nitkkr.techspardha.retrofit;

import com.nitkkr.techspardha.events.categoryPojo.CategoryData;
import com.nitkkr.techspardha.events.categoryPojo.EventCategory;
import com.nitkkr.techspardha.guestLecture.lecturesPojo.LectureData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {
    @GET("description")
    Observable<EventCategory> getData(
            @Query("eventCategory") String category,
            @Query("eventName") String eName


    );

    @GET("description")
    Observable<CategoryData> getCatData(
            @Query("eventCategory") String category


    );

    @GET(".")
    Observable<LectureData> getLectureData(

    );

}
