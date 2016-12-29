package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.Toast;

import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.model.Episode;
import com.swarmnyc.mvvmlib.sampleapp.services.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao on 12/29/16.
 */
public class RecyclerViewModel extends MvvmListViewModel {
    final String TAG = getClass().getName();
    private ObservableArrayList<EpisodeViewModel> mEpisodes = new ObservableArrayList<>();
    private DataService mDataService = new DataService();
    public ObservableBoolean mIsLoading = new ObservableBoolean( false );

    public RecyclerViewModel() {
    }

    protected RecyclerViewModel(Parcel in) {
    }

    @Override
    public ObservableArrayList getItemCollection() {
        return mEpisodes;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_episode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<RecyclerViewModel> CREATOR = new Creator<RecyclerViewModel>() {
        @Override
        public RecyclerViewModel createFromParcel(Parcel source) {
            return new RecyclerViewModel(source);
        }

        @Override
        public RecyclerViewModel[] newArray(int size) {
            return new RecyclerViewModel[size];
        }
    };

    protected void loadAllEpisodes() {
        mIsLoading.set(true);
        mDataService.getAllEpisodes(new Callback<List<Episode>>() {
            @Override
            public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
                mEpisodes.clear();
                List<Episode> episodes = response.body();
                Log.i(TAG, "onResponse: " + episodes.size());
                for (Episode e: episodes) {
                    mEpisodes.add( new EpisodeViewModel(e) );

                }
                mIsLoading.set(false);
            }

            @Override
            public void onFailure(Call<List<Episode>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
                t.printStackTrace();
                Toast.makeText(getContext().getAndroidContext(), "Network Failure:"+t.toString(), Toast.LENGTH_SHORT).show();
                mIsLoading.set(false);
            }
        });
    }

    @Override
    public void onInit( Bundle args ) {
//        loadAllEpisodes();
    }

    public Runnable refresh = new Runnable()
    {
        @Override
        public void run()
        {
            loadAllEpisodes();
        }
    };
    public ObservableBoolean getIsLoading()
    {
        return mIsLoading;
    }

}
