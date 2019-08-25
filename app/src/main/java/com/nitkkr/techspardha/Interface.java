package com.nitkkr.techspardha;

import com.nitkkr.techspardha.Pojo.CatData;
import com.nitkkr.techspardha.Pojo.EventCat;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {
    @GET("description")
    Observable<EventCat> getData(
            @Query("eventCategory") String category,
            @Query("eventName") String eName


    );

    @GET("description")
    Observable<CatData> getCatData(
            @Query("eventCategory") String category


    );
}
