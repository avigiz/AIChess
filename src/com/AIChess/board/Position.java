package com.AIChess.board;

public class Position {
    private int XCorr;
    private int YCorr;

    public Position(int x,int y){
        this.XCorr = x;
        this.YCorr = y;
    }

    public boolean IsPositionLegal(){
        if (XCorr < 8 && XCorr >= 0 && YCorr < 8 && YCorr >= 0)
            return true;
        else
            return false;
    }

    public int getXCorr() {
        return XCorr;
    }

    public int getYCorr() {
        return YCorr;
    }
}
