package com.example.ralph.motionandgesturesclass;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class GestureActivity extends AppCompatActivity {

    GestureOverlayView gestureOverlayView;
    GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        gestureLibrary = GestureLibraries.fromRawResource(this,R.raw.gestures);
        gestureLibrary.load();

        gestureOverlayView = findViewById(R.id.gestureOverlay);
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
                for(Prediction prediction:predictions){

                    Log.d("Prediction","Name: " + prediction.name + " Score: " + prediction.score);
                }
            }
        });
    }
}
