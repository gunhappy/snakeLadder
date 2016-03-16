package com.ske.snakebaddesign.models;

import android.content.DialogInterface;

import com.ske.snakebaddesign.activities.GameActivity;

import java.util.Observable;

/**
 * Created by พศิน on 14/3/2559.
 */
public class Game extends Observable {
    private Player player1;
    private Player player2;
    private Board board;
    private int turn;

    public Game(){
        initComponent();
    }

    private void initComponent(){
        player1 = new Player("Gun");
        player2 = new Player("Paii");
        board = new Board(8);
        turn = 0;
    }

    public void resetGame(){
        player1.getPiece().setPosition(0);
        player2.getPiece().setPosition(0);
        turn = 0;
        setChanged();
        notifyObservers();
    }

    public int rollDice(){
        if(getTurn()%2==0){
            return getPlayer1().rollDice();
        }else return getPlayer2().rollDice();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void moveCurrentPiece(int value) {
        if (getTurn() % 2 == 0) {
            getPlayer1().getPiece().setPosition(adjustPosition(getPlayer1().getPiece().getPosition(), value));
            setChanged();
            notifyObservers();
            checkWin();
            checkEffect(getPlayer1());
            setChanged();
            notifyObservers(getPlayer2().getName()+"'s Turn");
        } else {
            getPlayer2().getPiece().setPosition(adjustPosition(getPlayer2().getPiece().getPosition(), value));
            setChanged();
            notifyObservers();
            checkWin();
            checkEffect(getPlayer2());
            setChanged();
            notifyObservers(getPlayer1().getName()+"'s Turn");
        }
        nextTurn();
    }

    public void takeTurn() {
        final int value = rollDice();
        String title = "You rolled a die";
        String msg = "You got " + value;
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(value);
                dialog.dismiss();
            }
        };
        DataDialog d = new DataDialog(title, msg, listener);
        setChanged();
        notifyObservers(d);
    }

    public void checkEffect(Player player){
        if(getBoard().getSquareList().get(player.getPiece().getPosition()).hasEffect()){
            final int value = getBoard().getSquareList().get(player.getPiece().getPosition()).effect();
            String title = "You get extra move";
            String msg = "You move " + value;
            decreaseTurn();
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    moveCurrentPiece(value);
                    dialog.dismiss();
                }
            };
            DataDialog d = new DataDialog(title,msg,listener);
            setChanged();
            notifyObservers(d);
        }
    }

    public Board getBoard() {
        return board;
    }

    public int adjustPosition(int currentPos, int distance){
        currentPos += distance;
        int maxPos = board.getBoardSize() * board.getBoardSize() -1;
        if(currentPos > maxPos){
            currentPos =  maxPos - (currentPos - maxPos);
        }
        return currentPos;
    }

    public int getTurn() {
        return turn;
    }

    public void nextTurn(){
        this.turn++;
    }

    public void decreaseTurn(){
        this.turn--;
    }

    public void checkWin() {
        String title = "Game Over";
        String msg = "";
        if (getPlayer1().getPiece().getPosition() == getBoard().getBoardSize() * getBoard().getBoardSize() - 1) {
            msg = getPlayer1().getName()+" won!";
        } else if (getPlayer2().getPiece().getPosition() == getBoard().getBoardSize() * getBoard().getBoardSize() - 1) {
            msg = getPlayer2().getName()+" won!";
        } else {
            return;
        }

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
            }
        };
        DataDialog d = new DataDialog(title,msg,listener);
        this.setChanged();
        this.notifyObservers(d);
    }
}
