package com.AIChess.board;

import com.AIChess.Pieces.Piece;

/**
 * this class represent an occupied tile on chess board.
 */
public class OccupiedTile extends Tile {

    private final Piece pieceOnTheTile;

    public OccupiedTile(int x, int y, Piece piece) {
        super(x, y);
        this.pieceOnTheTile = piece;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return pieceOnTheTile;
    }

    @Override
    public String toString(){
            if (getPiece().getPieceAlliance().isBlack())
                return getPiece().toString().toLowerCase();
            else
                return getPiece().toString().toUpperCase();
    }
}
