package com.example.offerwall;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRequests {

    @GET("api/v1/entities/getAllIds")
    Call <Pojo> getBasicRequest () ;

    @GET("api/v1/object/{id}")
    Call <String>  getNextRequest (
    @Path("id") String id
    );
}