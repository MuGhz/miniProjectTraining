package com.example.muhammadghozi41.latihanlogin.service;

import com.example.muhammadghozi41.latihanlogin.model.ListMenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by muhammad.ghozi41 on 15/06/17.
 */

public interface PostService {

    @GET("/v2/{param}")
    Call<List<ListMenuItem>> listAllMenu(@Path("param") String param);
}
