package com.digitalmirko.noughtsandcrosses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void dropIn(View view){

        // User taps on space, gets the image user tapped on
        ImageView counter = (ImageView) view;

        // Moving 1,000 pixels up and off the screen
        counter.setTranslationY(-1000f);

        // sets the green.png to the image
        counter.setImageResource(R.drawable.green);

        // Animate back down in 500 mili seconds (short time) with 1 spin
        counter.animate().translationYBy(1000f).rotation(360).setDuration(500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
