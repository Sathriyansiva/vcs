package com.testing.admin.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by admin on 11/23/2016.
 */
public class MultiTouchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.tree);
        img.setMaxZoom(4f);
        setContentView(img);
    }
}