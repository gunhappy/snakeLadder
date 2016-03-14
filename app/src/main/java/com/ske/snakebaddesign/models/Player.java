package com.ske.snakebaddesign.models;

/**
 * Created by พศิน on 14/3/2559.
 */
public class Player {
    private String name;
    private  int position;

    public Player(String name) {
        this.name = name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
