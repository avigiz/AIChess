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

    protected static PieceType pieceType;
    protected final Position piecePosition;
    protected final Alliance Alliance;
    protected final boolean firstMoveDone;
    private final int cacheHashCode;

    public Piece(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance,PieceType pieceType) {
        this.piecePosition = new Position(pieceXCorr,pieceYCorr);
        this.Alliance = alliance;
        firstMoveDone = false;
        this.pieceType = pieceType;
        this.cacheHashCode = computeHashCode();
    }

    /**
     * calc the hash code of the current piece.
     * @return - the hash value of the current piece.
     */
    protected int computeHashCode(){
        int result = pieceType.hashCode();
        result = 31 * result + Alliance.hashCode();
        result = 31 * result + piecePosition.getXCorr() + piecePosition.getYCorr();
        if (firstMoveDone)
            result = 31 * result + 1;
        else
            result = 31 * result;
        return result;
    }

    /**
     * calculating all the possible moves for the piece.
     * @param board - the board's game.
     * @return - a list of the moves that the piece can do at this stage.
     */
    public abstract List<Move> calculateLegalMoves (Board board);


    /**
     * abstract class that move the piece by the given move.
     * @param move - the given move.
     * @return - the new piece that moved.
     */
    public abstract Piece movePiece(Move move);


    public static PieceType getType() {
        return pieceType;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }



    public Position getPosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance(){
        return this.Alliance;
    }

    public boolean getFirstMoveDone(){
        return this.firstMoveDone;
    }

    @Override
    public int hashCode(){
        return this.cacheHashCode;
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Piece))
            return false;
        Piece otherPiece = (Piece) other;
        return  piecePosition.equals(otherPiece.getPosition()) && pieceType == otherPiece.getPieceType() &&
                this.Alliance == otherPiece.getPieceAlliance() && this.firstMoveDone == otherPiece.firstMoveDone;
    }

}
