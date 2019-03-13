package com.AIChess.Pieces;

import com.AIChess.Alliance;
import com.AIChess.board.Board;
import com.AIChess.board.Move;
import com.AIChess.board.Position;

import java.util.List;

/**
 * this class represent a piece in a chess game.
 */
public abstract class Piece {

    protected final Position piecePosition;
    protected final Alliance Alliance;
    protected final boolean firstMoveDone;

    public Piece(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        this.piecePosition = new Position(pieceXCorr,pieceYCorr);
        this.Alliance = alliance;
        firstMoveDone = false;
    }

    /**
     * calculating all the possible moves for the piece.
     * @param board - the board's game.
     * @return - a list of the moves that the piece can do at this stage.
     */
    public abstract List<Move> calculateLegalMoves (Board board);

    public Position getPosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance(){
        return this.Alliance;
    }

    public boolean getFirstMoveDone(){
        return this.firstMoveDone;
    }
}
