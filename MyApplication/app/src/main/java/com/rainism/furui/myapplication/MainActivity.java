package com.rainism.furui.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rainism.furui.myapplication.getureview.GestureView;

public class MainActivity extends Activity {
    GestureView getsureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getsture_layout);
        getsureView = (GestureView) findViewById(R.id.getsureView);

        getsureView.setGestureListener(gestureListener);
    }
    GestureView.GestureListener gestureListener = new GestureView.GestureListener() {
        @Override
        public void confirm(String password) {
            Log.i("password",password);
        }
    };

}
