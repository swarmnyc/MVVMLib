package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

/**
 * Created by Tao on 9/16/16.
 */
public class DataBindingRecyclerViewAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
//    private final VM listViewModel;
    private final ObservableArrayList<MvvmViewModel> viewModels = new ObservableArrayList<>();
    @LayoutRes
    private final int layoutId;

    public DataBindingRecyclerViewAdapter(Context context, MvvmListViewModel listViewModel, @LayoutRes int layoutId)
    {
        this.inflater = LayoutInflater.from( context );
//        this.listViewModel = listViewModel;
        this.viewModels.addAll( listViewModel.getItemCollection() );
        this.layoutId = layoutId;

        viewModels.addOnListChangedCallback(new ListChangedCallbackForRecyclerView<MvvmViewModel>(this));
//        this.listViewModel.getEmojiCategories()
//                .addOnListChangedCallback(new ListChangedCallbackForRecyclerView<EmojiCollection>(this));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DataBindingViewHolder bindingViewHolder;
        ViewDataBinding vdb = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        bindingViewHolder = new DataBindingViewHolder( vdb );
        return bindingViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataBindingViewHolder viewHolder = (DataBindingViewHolder) holder;
        MvvmViewModel itemViewModel = this.viewModels.get( position );
        viewHolder.setData( itemViewModel );
    }

    @Override
    public int getItemCount() {
        return this.viewModels.size();
    }
}
