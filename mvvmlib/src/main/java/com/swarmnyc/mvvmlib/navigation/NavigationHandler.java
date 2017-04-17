package com.swarmnyc.mvvmlib.navigation;

import android.content.Context;
import android.os.Bundle;

public interface NavigationHandler {
    void navigate(Context context, Bundle args);
}
