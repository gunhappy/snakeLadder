package com.ske.snakebaddesign.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.snakebaddesign.R;
import com.ske.snakebaddesign.guis.BoardView;
import com.ske.snakebaddesign.models.DataDialog;
import com.ske.snakebaddesign.models.Die;
import com.ske.snakebaddesign.models.Game;
import com.ske.snakebaddesign.models.Player;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements Observer{

    private Game game;
    private BoardView boardView;
    private Button buttonTakeTurn;
    private Button buttonRestart;
    private TextView textPlayerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        game.resetGame();
    }

    private void initComponents() {
        game = new Game();
        game.addObserver(this);
        boardView = (BoardView) findViewById(R.id.board_view);
        boardView.setBoard(game.getBoard());
        buttonTakeTurn = (Button) findViewById(R.id.button_take_turn);
        buttonTakeTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.takeTurn();
            }
        });
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.resetGame();
            }
        });
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);
        textPlayerTurn.setText(game.getPlayer1().getName() + "'s Turn");
    }

    private void displayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }

    @Override
    public void update(Observable observable, Object data) {
        if(data == null) { //update position from Game
            boardView.setP1Position(game.getPlayer1().getPiece().getPosition());
            boardView.setP2Position(game.getPlayer2().getPiece().getPosition());
        }

        else if(data.getClass() == String.class){
            textPlayerTurn.setText(data.toString());
        }

        else { // Display dialog
            DataDialog d = (DataDialog) data;
            displayDialog(d.getTitle(), d.getMessage(), d.getListener());
        }
    }
}
