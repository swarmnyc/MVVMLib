package com.swarmnyc.mvvmlib.sampleapp;

import android.util.Log;

import com.jayway.awaitility.Awaitility;
import com.swarmnyc.mvvmlib.sampleapp.model.Episode;
import com.swarmnyc.mvvmlib.sampleapp.services.DataService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private DataService mDataService;
    private AtomicBoolean mCallbackComplete;

    @Before
    public void setUp() throws Exception {
        mDataService = new DataService();
        mCallbackComplete = new AtomicBoolean(false);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getAllEpisodes() throws Exception {
        mDataService.getAllEpisodes(new Callback<List<Episode>>() {
            @Override
            public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
                System.out.println("onResponse: " + response);
                List<Episode> episodes = response.body();
                for (Episode e: episodes) {
                    System.out.println("onResponse: " + e.id + " " + e.title);
                    System.out.println("price: " + e.price);
                    System.out.println("hlsURL: " + e.hlsURL);
                    System.out.println("type: " + e.type);
                    System.out.println("profilePictureUrl: " + e.user.profilePictureUrl);
                    System.out.println("imgUrl: " + e.show.imgUrl);
                    System.out.println("updateTime: " + e.updateTime);
                    System.out.println("email: " + e.email);

                }
                mCallbackComplete.set(true);
            }

            @Override
            public void onFailure(Call<List<Episode>> call, Throwable t) {
                System.out.println("onFailure: " + t);
                mCallbackComplete.set(true);
            }
        });

        Awaitility.await().atMost(50, TimeUnit.SECONDS).untilTrue(mCallbackComplete);
    }
}