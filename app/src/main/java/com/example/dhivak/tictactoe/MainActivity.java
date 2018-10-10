package com.example.dhivak.tictactoe;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningArray = {{0, 1, 2}, {3,4,5}, {6,7,8},
                            {0, 3, 6}, {1,4,7}, {2,5,8},
                            {0, 4, 8}, {2,4,6}
    };

    //true = x. false = o;
    int activePlayer = 1;

    boolean activeGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playTurn(View view) {
        ImageView counter = (ImageView) view;

        //Log.i("Tag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && activeGame) {
            gameState[tappedCounter] = activePlayer;

            //Place the pieces onto the grid
            if (activePlayer == 1) {
                counter.setTranslationY(-1000);
                counter.setImageResource(R.drawable.xpiece);
                counter.animate().translationYBy(1000).setDuration(1000);
                activePlayer = 0;

            } else {
                counter.setTranslationY(-1000);
                counter.setImageResource(R.drawable.opiece);
                counter.animate().translationYBy(1000).setDuration(1000);
                activePlayer = 1;

            }

            //Determine if someone has won the game or not
            for (int[] winningPos : winningArray) {
                if (gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[1]] == gameState[winningPos[2]] && gameState[winningPos[0]] != 2) {

                    activeGame = false;
                    if (activePlayer == 0) {
                        Toast.makeText(this, "X WINS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "O WINS", Toast.LENGTH_SHORT).show();
                    }

                    Button playAgain = (Button) findViewById(R.id.button);

                    playAgain.setVisibility(View.VISIBLE);
                }
                }
            } //End of for loop to check winners
        }

        public void playAgain(View view){

            Button playAgain = (Button) findViewById(R.id.button);

            playAgain.setVisibility(View.INVISIBLE);

            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

            for(int i =0; i<gridLayout.getChildCount(); i++){
                ImageView counter = (ImageView) gridLayout.getChildAt(i);

                counter.setImageDrawable(null);
            }

            for(int i=0; i<gameState.length; i++){
                gameState[i] = 2;
            }


            //true = x. false = o;
            activePlayer = 1;

            activeGame = true;


        }

    }




