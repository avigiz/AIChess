package com.AIChess.Pieces;

public enum PieceType {

    PAWN("P"),
    KNIGHT("N"),
    BISHOP("B"),
    ROOK("R"),
    KING("K"),
    QUEEN("Q");

    private String pieceType;



    PieceType (String pieceType){
        this.pieceType = pieceType;
    }

    @Override
    public String toString(){
        return this.pieceType;
    }
}
