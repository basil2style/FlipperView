package com.makeinfo.flipperview.network;

import retrofit.Callback;
import retrofit.http.GET;
import com.makeinfo.flipperview.model.Flower;

import java.util.List;

/**
 * Created by Basil on 8/21/2015.
 */
public interface api {

    @GET("/bins/44m6s")
    public void getData(Callback<List<Flower>> response);

}
