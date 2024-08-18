package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;  // This import is crucial

public class MainActivity extends AppCompatActivity {

    private ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();    // Initialize the views
        setListeners(); // Set the event listeners
    }

    private void initViews() {
        myImageView = (ImageView) findViewById(R.id.imageView1);
    }

    private void setListeners() {
        // Set a new OnTouchListener
        myImageView.setOnTouchListener(new MyOnTouchListener());
    }

    private final class MyOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView iv = (ImageView) v;
            if (iv == myImageView) {
                Toast.makeText(getApplicationContext(), "您好!Android!", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }
}
