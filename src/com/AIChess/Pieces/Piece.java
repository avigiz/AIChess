package com.AIChess.Pieces;

import com.AIChess.Alliance;
import com.AIChess.board.Board;
import com.AIChess.board.Move;
import com.AIChess.board.Position;

import java.util.List;

public abstract class Piece {

    protected final Position piecePosition;
    protected final Alliance Alliance;

    public Piece(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        this.piecePosition = new Position(pieceXCorr,pieceYCorr);
        this.Alliance = alliance;
    }
    public abstract List<Move> calculateLegalMoves (Board board);

    public Position getPosition(){
        return piecePosition;
    }

    public Alliance getPieceAlliance(){
        return this.Alliance;
    }
}
