package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.DataDialogViewModel;
import com.swarmnyc.mvvmlib.support.MvvmDialogFragment;

/**
 * Created by Tao on 7/6/17.
 */
public class FragmentDataDialog extends MvvmDialogFragment<DataDialogViewModel> {
	@Override protected int getLayoutResourceId() {
		return R.layout.fragment_data_dialog;
	}
}
