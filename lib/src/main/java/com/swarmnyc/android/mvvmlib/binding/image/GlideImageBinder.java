package com.swarmnyc.android.mvvmlib.binding.image;

import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

public class GlideImageBinder implements ImageBinder {
    @Override
    public void bind(ImageView view, String url, ImageCropType cropType, int errorDrawableId) {
        DrawableTypeRequest<String> image = Glide.with(view.getContext()).load(url);
        switch (cropType) {
            case Round:
                image.bitmapTransform(new CropCircleTransformation(view.getContext()));
                break;
            case Center:
                image.centerCrop();
                break;
            default:
                break;
        }

        if (errorDrawableId != 0) {
            image.error(errorDrawableId);
        }

        image.into(view);
    }
}
