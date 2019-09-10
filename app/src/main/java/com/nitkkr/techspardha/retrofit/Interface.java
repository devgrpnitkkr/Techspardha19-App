package com.nitkkr.techspardha.retrofit;

import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.SponsorshipData;
import com.nitkkr.techspardha.events.categoryPojo.CategoryData;
import com.nitkkr.techspardha.events.categoryPojo.EventCategory;
import com.nitkkr.techspardha.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.techspardha.root.RegisteredEvents.Registered;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;
import com.nitkkr.techspardha.root.userPojo.Udata;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Interface {
    @GET("events/description")
    Observable<EventCategory> getData(
            @Query("eventCategory") String category,
            @Query("eventName") String eName
    );

    @GET("events/description")
    Observable<CategoryData> getCatData(
            @Query("eventCategory") String category
    );

    @GET("sponsors/")
    Observable<SponsorshipData> getSponsorship(
    );

    @GET("lectures")
    Observable<LectureData> getLectureData(
    );

    @POST("loginApp")
    @FormUrlEncoded
    Observable<Udata> getData(
            @Field("idToken") String token
            );

    @PUT("signUpApp")
    @FormUrlEncoded
    Observable<RegisterData> registerUser(
            @Field("phone") String phone,
            @Field("college") String college,
            @Field("year") String year,
            @Field("branch") String branch,
            @Field("email") String email

    );

    @GET("user/eventApp")
    Observable<Registered> getRegisteredEvents(
            @Query("email") String email
    );




}
