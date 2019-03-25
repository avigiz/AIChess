package com.AIChess.board;

import com.AIChess.Pieces.Piece;

/**
 * this class represent an empty tile in chess board.
 */
public class EmptyTile extends Tile {
    public EmptyTile(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public String toString(){
        return "-";
    }
}
