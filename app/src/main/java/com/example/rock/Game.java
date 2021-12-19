package com.example.rock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class Game extends AppCompatActivity {
    int playerSelection = -1;
    int computerSelection = -1;
    HashMap<Integer, String> map = new HashMap<>();
    TextView computer, computerS, playerS;
    TextView winner;
    int playerScore = 0;
    int computerScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        map.put(0, "Rock");
        map.put(1, "Paper");
        map.put(2, "Scissor");

        Button rock = findViewById(R.id.rock);
        Button paper = findViewById(R.id.paper);
        Button scissor = findViewById(R.id.scissor);





        TextView player = findViewById(R.id.playerPlay);
        computer = findViewById(R.id.computerPlay);
        playerS = findViewById(R.id.playerScore);
        computerS = findViewById(R.id.computerScore);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSelection = 0;
                player.setText(map.get(playerSelection));
                selectComputer();
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSelection = 1;
                player.setText(map.get(playerSelection));
                selectComputer();
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSelection = 2;
                player.setText(map.get(playerSelection));
                selectComputer();
            }
        });

    }

    public void selectComputer() {
        Random r = new Random();
        computerSelection = r.nextInt(3);
        computer.setText(map.get(computerSelection));
        winner = findViewById(R.id.winner);

        getWinner();
    }




    private void getWinner() {
        if (computerSelection!=playerSelection) {
            int winnerWithComputer = (computerSelection + 1)%3;
            if (winnerWithComputer==playerSelection) {
                playerScore++;
                playerS.setText("Player: "+playerScore);
            } else {
                computerScore++;
                computerS.setText("Computer: " + computerScore);
            }
        }



        if (playerScore == 5 || computerScore ==5) {
            if (playerScore == 5) winner.setText("You Won");
            else winner.setText("You lost");

            Button restart = findViewById(R.id.buttonRestart);
            Button exit = findViewById(R.id.buttonExit);

            exit.setVisibility(View.VISIBLE);
            restart.setVisibility(View.VISIBLE);




        }

    }


    public void restartGame(View view) {

        Intent intent = new Intent(Game.this, Game.class);
        startActivity(intent);
    }

    public void exitGame(View view) {
         Intent a = new Intent(Intent.ACTION_MAIN);
         a.addCategory(Intent.CATEGORY_HOME);
         a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         startActivity(a);

    }
}