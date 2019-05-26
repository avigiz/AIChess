package com.AIChess.board;

/**
 * this class represent a position on a chess board.
 */
public class Position {
    //X represent the rows.
    //Y represent the cols.
    private int XCorr;
    private int YCorr;

    public Position(int x,int y){
        this.XCorr = x;
        this.YCorr = y;
    }

    /**
     * check if a given position is legal.
     * @return - true if the position is legal.
     */
    public boolean IsPositionLegal(){
        if ((XCorr < 8 && XCorr >= 0) && (YCorr < 8 && YCorr >= 0))
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

    public boolean equals(Position pos){
        if (this.XCorr == pos.getXCorr() && this.YCorr == pos.getYCorr())
            return true;
        return false;
    }
}
