package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

// Regular Activity which don't use MVVMLib
public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_second, container, false);

        final Bundle bundle = getArguments();

        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setText(bundle.getString(Keys.PASS_VALUE));

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString(Keys.PASS_VALUE, editText.getText().toString());
                ((MvvmFragment)getTargetFragment()).onResult(getTargetRequestCode(),1, bundle);
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}
