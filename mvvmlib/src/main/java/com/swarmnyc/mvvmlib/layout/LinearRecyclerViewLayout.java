package com.swarmnyc.mvvmlib.layout;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.swarmnyc.mvvmlib.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by Tao on 9/13/16.
 */
public class LinearRecyclerViewLayout implements BaseRecyclerViewLayout {
    @Override
    public void setViewLayout(RecyclerView view) {
        Resources resources = view.getResources();
        Context context = view.getContext();
        final LinearLayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
                false);
        view.setLayoutManager(layout);


    }

}