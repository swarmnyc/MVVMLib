package com.swarmnyc.mvvmlib.sampleapp.services;

import android.support.annotation.NonNull;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.swarmnyc.mvvmlib.sampleapp.model.Episode;

import java.io.IOException;
import java.util.List;

/**
 * Created by Tao on 1/3/17.
 */
public class EpisodesSupplier implements Supplier<Result<List<Episode>>> {
    private DataService mDataService = new DataService();
    final int count = 15;
    int page = 0;
    public EpisodesSupplier() {
    }

    @NonNull
    @Override
    public Result<List<Episode>> get() {
        try {
            List<Episode> episodes = mDataService.getEpisodes(count/*limit*/, page*count/*skip*/);
            if (episodes != null) {
                page ++;
                return Result.present(episodes);
            }
        } catch (IOException e) {
            return Result.failure(e);
        }
        return Result.failure();
    }
}
