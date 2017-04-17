package com.swarmnyc.mvvmlib.binding.image;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.swarmnyc.mvvmlib.AndroidUtils;

public class GlideImageBinder implements ImageBinder {
    @Override
    public void bind(ImageView view, Uri uri, ImageCropType cropType, int errorDrawableId) {
        Context context = AndroidUtils.getContext(view);
        DrawableTypeRequest<Uri> request = Glide.with(context).load(uri);
        switch (cropType) {
            case Round:
                request.bitmapTransform(new CropCircleTransformation(context));
                break;
            case Center:
                request.centerCrop();
                break;
            default:
                break;
        }

        if (errorDrawableId != 0) {
            request.error(errorDrawableId);
        }
        request.listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                Log.w("GlideImageBinder", model.toString(), e);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        });
        request.into(view);
    }
}
