package com.ske.snakebaddesign.models;

import android.graphics.Color;
import android.util.Log;

import java.util.Random;

/**
 * Created by พศิน on 16/3/2559.
 */
public class ForwardSquare extends Square {

    public ForwardSquare(int number) {
        super(number, Color.parseColor("#048916"));
    }

    public int effect(){
        return ((int)(Math.random()*6))+1;
    }

    public boolean hasEffect(){return true;}

}
