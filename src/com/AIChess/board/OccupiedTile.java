package com.AIChess.board;

import com.AIChess.Pieces.Piece;

public class OccupiedTile extends Tile {

    private final Piece pieceOnTheTile;

    public OccupiedTile(int x, int y, Piece piece) {
        super(x, y);
        this.pieceOnTheTile = piece;
    }

    @Override
    public boolean isTileOccupaied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return pieceOnTheTile;
    }
}
