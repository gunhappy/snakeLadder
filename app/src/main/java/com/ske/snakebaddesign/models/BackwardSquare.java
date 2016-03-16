package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by พศิน on 16/3/2559.
 */
public class BackwardSquare extends Square {

    public BackwardSquare(int number) {
        super(number, Color.parseColor("#FF091A"));
    }

    public int effect(){
        return (((int)(Math.random()*6))+1)*-1;
    }

    public boolean hasEffect(){return true;}
}
