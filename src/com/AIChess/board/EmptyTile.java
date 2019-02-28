package com.AIChess.board;

import com.AIChess.Pieces.Piece;

public class EmptyTile extends Tile {
    public EmptyTile(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isTileOccupaied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
