package com.swarmnyc.mvvmlib;

import android.os.Bundle;

public interface FragmentWrapper {
    void onResult(int requestCode, int resultCode, Bundle bundle);
}
