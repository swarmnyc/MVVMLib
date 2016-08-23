package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.sampleapp.R;

// Regular Activity which don't use MVVMLib
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra(Keys.ARGS);

        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(bundle.getString(Keys.PASS_VALUE));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                bundle.putString(Keys.PASS_VALUE, editText.getText().toString());
                resultIntent.putExtra(Keys.ARGS, bundle);
                setResult(0, resultIntent);
                finish();
            }
        });
    }
}
