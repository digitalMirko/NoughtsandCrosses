package com.digitalmirko.noughtsandcrosses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = green (X), 1 = red (O)
    int activePlayer = 0;

    // Is game active
    boolean gameIsActive = true;

    // Memory for managing game state, 2 means unplayed, nothing in that slot 0-9

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // All the winning positions
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8}, // Horizontal Winning Positions
                                {0,3,6},{1,4,7},{2,5,8}, // Vertical Winning Positions
                                {0,4,8},{2,4,6}};  // Angular Winning Positions

    public void dropIn(View view){

        // User taps on space, gets the image user tapped on
        ImageView counter = (ImageView) view;

        // get the image tags
//        System.out.println(counter.getTag().toString());

        // 0 to 8, tapped area counter
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // Point on board hasnt been tapped yep its ok to play and game is active
        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            // Moving 1,000 pixels up and off the screen
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.darkgreen);

                // red to go next
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.darkred);

                // green to go next
                activePlayer = 0;
            }

            // sets the green.png to the image
//        counter.setImageResource(R.drawable.green);

            // Animate back down in 100 mili seconds (short time) with 1 spin
            counter.animate().translationYBy(1000f).rotation(360).setDuration(100);

            // check to see if theres a winner
            for (int[] winningPosition: winningPositions) {

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){

                    // print out winner to console, checking logic to see if its working
//                    System.out.println(gameState[winningPosition[0]]);

                    // Game is no longer active
                    gameIsActive = false;

                    // Who has won
                    String winner = "O";
                    if (gameState[winningPosition[0]] == 0){

                        winner = "X";
                    }

                    gameWord(winner);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState ) {

                        if (counterState == 2) gameIsOver = false;
                    }
                        if(gameIsOver) {

                            gameWord("draw");
                    }
                }
            }
        }
    }

    // Game Winner pop up box method
    public void gameWord (String word){

        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);

        if (word == "X" || word == "O") {

            winnerMessage.setText(word + " has won!");
        }  else {
            winnerMessage.setText("It's a draw!");
        }

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.VISIBLE);
    }

    // Allows game to be played again
    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++){

            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            // resets images to blank
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
