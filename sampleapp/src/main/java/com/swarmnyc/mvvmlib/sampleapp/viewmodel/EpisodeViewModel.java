package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.net.Uri;
import android.os.Parcel;
import android.util.Log;

import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.sampleapp.model.Episode;

/**
 * Created by Tao on 12/29/16.
 */

public class EpisodeViewModel extends MvvmViewModel {
    public static final Creator<EpisodeViewModel>
            CREATOR
            = new Creator<EpisodeViewModel>() {
        @Override
        public EpisodeViewModel createFromParcel(Parcel source) {
            return new EpisodeViewModel(source);
        }

        @Override
        public EpisodeViewModel[] newArray(int size) {
            return new EpisodeViewModel[size];
        }
    };
    final Uri mDefaultPic = Uri.parse("https://swarm-website-uploads.s3.amazonaws.com/teammember-575ae2a527f37e153015b9fd.jpg");

    final String TAG = getClass().getName();
    Episode mEpisode;

    public EpisodeViewModel(final Episode episode) {
//        Log.i(TAG, "EpisodeViewModel: " + episode);
        mEpisode = episode;
    }

    protected EpisodeViewModel(Parcel in) {
        this.mEpisode = in.readParcelable(Episode.class.getClassLoader());
    }

    public String getTitle() {
//        Log.i(TAG, "getTitle: " + mEpisode.title);
        return mEpisode.title;
    }

    public String getImageUrlString() {
        Log.i(TAG, "getImageUrlString: " + mEpisode.user.profilePictureUrl);
        return mEpisode.user.profilePictureUrl;
    }

    public Uri getAvatarUri() {
        if (mEpisode.user == null || mEpisode.user.profilePictureUrl == null) return mDefaultPic;

        return Uri.parse(mEpisode.user.profilePictureUrl);
    }

    public Uri getImageUri() {
        if (mEpisode.show == null || mEpisode.show.imgUrl == null) return mDefaultPic;

        return Uri.parse(mEpisode.show.imgUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}