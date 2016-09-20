package com.swarmnyc.mvvmlib.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.tool.util.StringUtils;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.R;
import com.swarmnyc.mvvmlib.binding.image.ImageCropType;

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

    @BindingAdapter({"mvvm:onClick"})
    public static void bindOnClick(final View view, final Runnable runnable) {

        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( final View view )
            {
                runnable.run();
            }
        } );
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
}
