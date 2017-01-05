package com.swarmnyc.mvvmlib.layout;

/**
 * Created by Tao on 9/17/16.
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.swarmnyc.mvvmlib.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

/**
 * Created by Tao on 9/15/16.
 */
public class GridRecyclerViewLayout implements BaseRecyclerViewLayout {

    final int numColumns;

    public GridRecyclerViewLayout() {
        numColumns = 4;
    }
    public GridRecyclerViewLayout(int numColumns) {
        this.numColumns = numColumns;
    }

    @Override
    public void setViewLayout(RecyclerView view) {
        Context context = view.getContext();
        GridLayoutManager layout = new GridLayoutManager( context, numColumns, LinearLayoutManager.VERTICAL, false );
        view.setLayoutManager(layout);
        if (view.getTag(R.id.itemDecoration) == null) {
            view.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).colorResId(android.R.color.transparent).marginResId(R.dimen.space_0_5x)
                    .build());
            view.addItemDecoration(new VerticalDividerItemDecoration.Builder(context).colorResId(android.R.color.transparent).marginResId(R.dimen.space_0_5x)
                    .build());
            view.setTag(R.id.itemDecoration, true);
        }
    }
}
