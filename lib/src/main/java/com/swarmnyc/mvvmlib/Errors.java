package com.swarmnyc.mvvmlib;

public class Errors {
    public static String no_context = "In order to use MVVMLib, you have to new a MvvmContext";
    public static String is_null = "%s can't be null";
    public static String no_view_data_binding = "MVVMLib Cannot do binding when ViewDataBinding is null";
    public static String no_viewmodel_type = "In order to active ViewModel, MvvmActivity should be assigned like class YourActivity extends MvvmActivity<YourViewModel>{}";
    public static String create_viewmodel = "MVVMLib Cannot create ViewModel";
    public static String fnh_need_activity = "FragmentNavigationHandler needs Activity to navigate";
    public static String fail_create_fragment = "Fragment can't be created";

    public static String is_null(String para) {
        return String.format(is_null, para);
    }
}
