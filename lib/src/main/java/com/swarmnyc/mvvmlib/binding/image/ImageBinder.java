package com.swarmnyc.mvvmlib.binding.image;

import android.widget.ImageView;

public interface ImageBinder {
    void bind(ImageView view, String url, ImageCropType cropType, int errorDrawableId);
}
