package com.ske.snakebaddesign.models;

import java.util.Random;

/**
 * Created by พศิน on 14/3/2559.
 */
public class Die {
    private final int minimumPoint = 1;
    private final int maximumPoint = 6;
    private Random random;

    public Die(){
        random = new Random();
    }

    public int roll(){
        return  random.nextInt(maximumPoint)+minimumPoint;
    }

}
