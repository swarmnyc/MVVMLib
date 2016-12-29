package com.swarmnyc.mvvmlib.sampleapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.Date;
import java.util.List;

/**
 * Created by Tao on 12/28/16.
 */
@Type("episode")
@JsonIgnoreProperties(ignoreUnknown = true)
// TODO set to true for release otherwise this will crash as new attributes are added
public class Episode implements Parcelable {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class User {
        public String profilePictureUrl;
    };
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Show {
        public String imgUrl;
    };

    @Id
    public String id;
    public String type;
    public String description;

    public List<String> tags;
    public double price;

    public User user;
    public Show show;

    @JsonProperty("updated-at")
    public Date updateTime;

    @JsonProperty("hls-url")
    public String hlsURL;

    public String title;
    public String email;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
