package com.swarmnyc.android.mvvmlib.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.tool.util.StringUtils;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.swarmnyc.android.mvvmlib.MvvmContext;
import com.swarmnyc.android.mvvmlib.R;
import com.swarmnyc.android.mvvmlib.binding.image.ImageCropType;

public class BindingUtils {
    @BindingConversion
    public static String convertBindableToString(BindableString bindableString) {
        return bindableString.get();
    }

    @BindingConversion
    public static boolean convertBindableToBoolean(BindableBoolean bindableBoolean) {
        return bindableBoolean.get();
    }

    @BindingAdapter({"mvvm:visibility"})
    public static void bindVisibility(final View view, final boolean value) {
        if (value) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
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
    public static void bindImage(final ImageView view, final String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUrl, ImageCropType.Center, 0);
        }
    }

    @BindingAdapter({"mvvm:image", "mvvm:imageError"})
    public static void bindImage(final ImageView view, final String imageUrl, @DrawableRes final int resId) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUrl, ImageCropType.Center, resId);
        }
    }

    @BindingAdapter({"mvvm:image", "mvvm:imageCropType"})
    public static void bindImage(final ImageView view, final String imageUrl, final ImageCropType type) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUrl, type, 0);
        }
    }

    @BindingAdapter({"mvvm:image", "mvvm:imageCropType", "mvvm:imageError"})
    public static void bindImage(final ImageView view, final String imageUrl, final ImageCropType type, @DrawableRes final int resId) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            MvvmContext.getContext(view).getImageBinder().bind(view, imageUrl, type, resId);
        }
    }
}
