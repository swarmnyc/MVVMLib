package com.swarmnyc.mvvmlib.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.tool.util.StringUtils;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Observable;
import com.google.android.agera.Updatable;
import com.google.common.util.concurrent.Runnables;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.R;
import com.swarmnyc.mvvmlib.adapter.DataBindingRecyclerViewAdapter;
import com.swarmnyc.mvvmlib.binding.image.ImageCropType;
import com.swarmnyc.mvvmlib.layout.BaseRecyclerViewLayout;
import com.swarmnyc.mvvmlib.layout.GridRecyclerViewLayout;
import com.swarmnyc.mvvmlib.layout.LinearRecyclerViewLayout;

public class BindingUtils {

    @BindingConversion
    public static String convertBindableToString(BindableString bindableString) {
        return bindableString.get();
    }

    @BindingConversion
    public static boolean convertBindableToBoolean(BindableBoolean bindableBoolean) {
        return bindableBoolean.get();
    }



    @BindingConversion
    public static Uri convertStringToUri(BindableString string) {
        if (string != null && !string.isEmpty()) {
            return Uri.parse(string.get());
        } else {
            return null;
        }
    }

    @BindingConversion
    public static Uri convertStringToUri(String string) {
        if (string != null && !string.isEmpty()) {
            return Uri.parse(string);
        } else {
            return null;
        }
    }

    @BindingConversion
    public static Uri convertBindableUriToUri(BindableUri uri) {
        if (uri != null) {
            return uri.get();
        } else {
            return null;
        }
    }

    @BindingAdapter({"mvvm:visibility"})
    public static void bindVisibility(final View view, final boolean value) {
        if (value) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter( {"mvvm:refreshing"} )
    public static void bindRefreshing( final SwipeRefreshLayout view, final boolean value )
    {
        view.setRefreshing( value );
    }

    @BindingAdapter({"mvvm:onRefresh"})
    public static void bindOnRefresh(final SwipeRefreshLayout view, final Runnable runnable) {

        view.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                runnable.run();
            }
        } );
    }

    @BindingAdapter({"mvvm:text"})
    public static void bindEditText(EditText view, final BindableString observableString) {
        Pair<BindableString, TextWatcherAdapter> pair = (Pair) view.getTag(R.id.bound_observable);

        if (pair == null || pair.first != observableString) {
            if (pair != null) {
                view.removeTextChangedListener(pair.second);
            }

            TextWatcherAdapter watcher = new TextWatcherAdapter() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    observableString.set(s.toString());
                }
            };

            view.setTag(R.id.bound_observable, new Pair<>(observableString, watcher));
            view.addTextChangedListener(watcher);
        }

        String newValue = observableString.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
            view.setSelection(newValue.length());
        }
    }

    @BindingAdapter({"mvvm:htmlText"})
    public static void bindHtmlText(final TextView textView, final String text) {
        if (StringUtils.isNotBlank(text)) {
            textView.setText(Html.fromHtml(text));
        }
    }

    @BindingAdapter({"mvvm:linkTo"})
    public static void bindLinkTo(final View view, @StringRes final int resId) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvvmContext.getContext(view).getNavigationManager().linkTo(view.getContext().getString(resId));

            }
        });
    }

    @BindingAdapter({"mvvm:linkTo"})
    public static void bindLinkTo(final View view, final String url) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvvmContext.getContext(view).getNavigationManager().linkTo(url);
            }
        });
    }

    @BindingAdapter({"mvvm:image"})
    public static void bindImage(final ImageView view, final Uri imageUri) {
        if (imageUri != null) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUri, ImageCropType.Center, 0);
        }
    }

    @BindingAdapter({"mvvm:image", "mvvm:imageError"})
    public static void bindImage(final ImageView view, final Uri imageUri, @DrawableRes final int resId) {
        if (imageUri != null) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUri, ImageCropType.Center, resId);
        }
    }

    @BindingAdapter({"mvvm:image", "mvvm:imageCrop"})
    public static void bindImage(final ImageView view, final Uri imageUri, final ImageCropType type) {
        if (imageUri != null) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUri, type, 0);
        }
    }

    @BindingAdapter({"mvvm:image", "mvvm:imageCrop", "mvvm:imageError"})
    public static void bindImage(final ImageView view, final Uri imageUri, final ImageCropType type, @DrawableRes final int resId) {
        if (imageUri != null) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUri, type, resId);
        }
    }

    @BindingAdapter({"mvvm:drawable"})
    public static void bindDrawable(final ImageView view, final @DrawableRes int redId) {
        view.setImageResource(redId);
    }

//    @BindingAdapter({"mvvm:list", "mvvm:itemLayoutId"})
//    public static void bindList(final RecyclerView view, final MvvmListViewModel listViewModel, final @LayoutRes
//    int layoutId) {
//        LinearRecyclerViewLayout layout = LinearRecyclerViewLayout.newInstance();
//        layout.setViewLayout(view);
//
//        RecyclerView.Adapter adapter = new DataBindingRecyclerViewAdapter(
//                view.getContext(), listViewModel, layoutId);
//        view.setAdapter(adapter);
//    }
    @BindingAdapter({"mvvm:list", "mvvm:numColumns"})
    public static void bindList(final RecyclerView view, final MvvmListViewModel listViewModel, final
    int numColumns) {
        BaseRecyclerViewLayout layout;
        if (numColumns > 1) {
            layout = new GridRecyclerViewLayout(numColumns);
        } else {
            layout = new LinearRecyclerViewLayout();
        }
        layout.setViewLayout(view);

        RecyclerView.Adapter adapter = new DataBindingRecyclerViewAdapter(
                view.getContext(), listViewModel, listViewModel.getItemLayoutId());
        view.setAdapter(adapter);
    }

    @BindingAdapter({"mvvm:list"})
    public static void bindList(final RecyclerView view, final MvvmListViewModel listViewModel) {
        LinearRecyclerViewLayout layout = new LinearRecyclerViewLayout();
        layout.setViewLayout(view);

        RecyclerView.Adapter adapter = new DataBindingRecyclerViewAdapter(
                view.getContext(), listViewModel, listViewModel.getItemLayoutId());
        view.setAdapter(adapter);
    }

    @BindingAdapter({"mvvm:listData", "mvvm:listItemView"})
    public static void bindList(final RecyclerView view, final ObservableArrayList listViewModel, @LayoutRes final
    int viewLayoutId) {
        LinearRecyclerViewLayout layout = new LinearRecyclerViewLayout();
        layout.setViewLayout(view);

        RecyclerView.Adapter adapter = new DataBindingRecyclerViewAdapter(
                view.getContext(), listViewModel, viewLayoutId);
        view.setAdapter(adapter);

    }

    @BindingAdapter({"mvvm:onClick"})
    public static void bindOnClick(final View view, final Runnable runnable) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                runnable.run();
            }
        });

    }
}
