package com.valentishealth.app.presshub.api;

import com.valentishealth.app.presshub.model.Modelist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    /* This function contains actual path to endpoint of the remaining part of the base_url.
     * The function loads a list of records found from the endpoint */
    @GET("/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json")
    Call<List<Modelist>> loadData();
}
