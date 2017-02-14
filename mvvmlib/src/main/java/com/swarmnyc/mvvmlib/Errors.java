package com.swarmnyc.mvvmlib;

public class Errors {
    public static final String UNEXPECTED = "There is a unexpected thing";
    public static final String NO_CONTEXT = "In order to use MVVMLib, you have to new a MvvmContext";
    public static final String IS_NULL = "%s can't be null";
    public static final String NO_VIEW_DATA_BINDING = "MVVMLib Cannot do binding when ViewDataBinding is null";
    public static final String NO_VIEWMODEL_TYPE = "In order to active ViewModel, MvvmActivity should be assigned like class YourActivity extends MvvmActivity<YourViewModel>{}";
    public static final String CREATE_VIEWMODEL = "MVVMLib Cannot create ViewModel";
    public static final String FNH_NEED_ACTIVITY = "FragmentNavigationHandler needs Activity to navigate";
    public static final String FAIL_CREATE_FRAGMENT = "Fragment can't be created";

    public static String isNull(String para) {
        return String.format(IS_NULL, para);
    }
}
