package com.swarmnyc.mvvmlib;

import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ViewModelUtils {
    public static Object createViewModel(Class sourceClass) {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) sourceClass.getGenericSuperclass();
            Class viewModelClass = (Class) parameterizedType.getActualTypeArguments()[0];
            return viewModelClass.newInstance();
        } catch (Exception e) {
            Log.e("ViewModelUtils", "Error in createViewModel ([sourceClass])", e);
            throw new RuntimeException(Errors.CREATE_VIEWMODEL, e);
        }
    }

    public static boolean assignFromViewModel(Class sourceClass) {
        Type type = sourceClass.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class viewModelClass = (Class) parameterizedType.getActualTypeArguments()[0];

            return MvvmViewModel.class.isAssignableFrom(viewModelClass);
        } else {
            return false;
        }
    }
}
