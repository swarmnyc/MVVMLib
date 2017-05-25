package com.swarmnyc.mvvmlib.binding.image;

import android.net.Uri;
import android.widget.ImageView;

public interface ImageBinder {
    void bind(ImageView view, Uri uri, ImageCropType cropType, int errorDrawableId);
}
