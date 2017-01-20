package com.digitalmirko.noughtsandcrosses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 0 = green (X), 1 = red (O)
    int activePlayer = 0;

    // Memory for managing game state, 2 means unplayed, nothing in that slot 0-9

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    public void dropIn(View view){

        // User taps on space, gets the image user tapped on
        ImageView counter = (ImageView) view;

        // get the image tags
        System.out.println(counter.getTag().toString());

        // 0 to 8, tapped area counter
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // Point on board hasnt been tapped yep its ok to play
        if (gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;

            // Moving 1,000 pixels up and off the screen
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.green);

                // red to go next
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);

                // green to go next
                activePlayer = 0;
            }

            // sets the green.png to the image
//        counter.setImageResource(R.drawable.green);

            // Animate back down in 500 mili seconds (short time) with 1 spin
            counter.animate().translationYBy(1000f).rotation(360).setDuration(500);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
