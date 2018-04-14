package com.example.ralph.motionandgesturesclass;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    ViewGroup rootLayout;
    VelocityTracker velocityTracker;
    HashMap<Integer,View> viewHashMap = new HashMap<>();

    GestureDetector gestureDetector;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);

        gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("SimpleGesture","Simple Fling");
                return true;
            }
        });
        rootLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                gestureDetector.onTouchEvent(event);

                int action = event.getActionMasked();
                int index = event.getActionIndex();
                int id = event.getPointerId(index);

                float x = event.getX(index);
                float y = event.getY(index);
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //Toast.makeText(MainActivity.this,"ActionDown",Toast.LENGTH_SHORT).show();
                        View dView = new View(MainActivity.this);
                        viewHashMap.put(id,dView);
                        dView.setLayoutParams(new RelativeLayout.LayoutParams(100,100));
                        dView.setBackgroundColor(Color.BLACK);
                        dView.setX(x);
                        dView.setY(y);
                        rootLayout.addView(dView);

//                        velocityTracker = VelocityTracker.obtain();
//                        velocityTracker.addMovement(event);

                        return true;

                    case MotionEvent.ACTION_MOVE:
                        //velocityTracker.addMovement(event);

                        for(int i = 0;i<event.getPointerCount();i++){
                            int pid = event.getPointerId(i);
                            float pX = event.getX(i);
                            float pY = event.getY(i);
                            View mView = viewHashMap.get(pid);
                            mView.setX(pX);
                            mView.setY(pY);
                        }

                        //velocityTracker.computeCurrentVelocity(1000);
                        //Log.d("Velocity","X:" + velocityTracker.getXVelocity());
                        //Log.d("Velocity","Y:" + velocityTracker.getYVelocity());

                        return true;


                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        View uView = viewHashMap.get(id);
                        viewHashMap.remove(id);
                        //velocityTracker.addMovement(event);
                        rootLayout.removeView(uView);
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("GestureDetector","Down event");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("GestureDetector","Show Press event");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("GestureDetector","Single Tap Up event");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("GestureDetector","Scroll event");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("GestureDetector","Long Press event");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("GestureDetector","Fling event");
        return true;
    }
}
