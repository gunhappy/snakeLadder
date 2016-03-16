package com.ske.snakebaddesign.models;

/**
 * Created by พศิน on 14/3/2559.
 */
public class Player {
    private String name;
    private Piece piece;
    private Die die;

    public Player(String name) {
        this.name = name;
        piece = new Piece();
        die = new Die();
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }

    public int rollDice(){
        return die.roll();
    }
}
