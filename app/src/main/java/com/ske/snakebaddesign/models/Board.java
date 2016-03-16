package com.ske.snakebaddesign.models;

import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by พศิน on 14/3/2559.
 */
public class Board {
    private int boardSize;
    private List<Square> squareList;

    public Board(int boardSize){
        squareList = new ArrayList<Square>();
        this.boardSize = boardSize;
        int[] forward = {5,18,25,46};
        int[] backward = {10,22,43,60};

        for(int i=0;i<boardSize*boardSize;i++){
            if(contain(forward,i)) squareList.add(new ForwardSquare(i));
            else if(contain(backward,i)) squareList.add(new BackwardSquare(i));
            else squareList.add(new NormalSquare(i));
        }

    }

    public boolean contain(int[] arr,int k){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==k) return true;
        }
        return false;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<Square> getSquareList() {
        return squareList;
    }
}
