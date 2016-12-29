package com.swarmnyc.mvvmlib.sampleapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;
import com.swarmnyc.mvvmlib.sampleapp.BuildConfig;
import com.swarmnyc.mvvmlib.sampleapp.model.Episode;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by Tao on 12/28/16.
 */

public class DataService {
    private Api api;

    public DataService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkHttpClient httpClient = builder.readTimeout(30, TimeUnit.SECONDS).build();

        ObjectMapper objectMapper = new ObjectMapper();

        api = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(new JSONAPIConverterFactory(objectMapper,
                        Episode.class
                ))
                .client(httpClient)
                .build()
                .create(Api.class);
    }

    public void getAllEpisodes(final Callback<List<Episode>> callback) {
        api.getEpisodes().enqueue(callback);
    }


    public interface Api {
        @GET("episodes/")
        Call<List<Episode>> getEpisodes();

    }

}
