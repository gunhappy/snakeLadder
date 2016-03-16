package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by พศิน on 14/3/2559.
 */
public abstract class Square {
    private int number;
    private int color;
    public Square(int number, int color){
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public int getColor(){
        return this.color;
    }

    public int effect(){return 0;}

    public boolean hasEffect(){return false;}
}
