package com.AIChess.Pieces;

import com.AIChess.board.*;
import com.AIChess.board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent a knight in a chess game.
 */
public class Knight extends Piece {

    public Knight(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance,PieceType.KNIGHT);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        Position destinationPosition;
        List<Move> legalMoves = new ArrayList<>();
        ArrayList<Position> allCandidatesPosition = new ArrayList<>();
        //check all the 8 possible of the knight.
        allCandidatesPosition.add(new Position(1,2));
        allCandidatesPosition.add(new Position(1,-2));
        allCandidatesPosition.add(new Position(-1,2));
        allCandidatesPosition.add(new Position(-1,-2));
        allCandidatesPosition.add(new Position(2,1));
        allCandidatesPosition.add(new Position(2,-1));
        allCandidatesPosition.add(new Position(-2,1));
        allCandidatesPosition.add(new Position(-2,-1));

        for (Position candidateTile : allCandidatesPosition) {
            destinationPosition = new Position(candidateTile.getXCorr()+ this.piecePosition.getXCorr(),candidateTile.getYCorr() + piecePosition.getYCorr());

            //check if the destination position is legal.
            if (destinationPosition.IsPositionLegal()) {
                Tile candidateDestinationTile = board.getTile(destinationPosition);
                //check if the tile is empty.
                if (!candidateDestinationTile.isTileOccupied())
                    legalMoves.add(new Move.regularMove(board,this,candidateDestinationTile.getPosition()));
                //check if the piece on the tile is an enemy piece.
                else
                {
                    if (candidateDestinationTile.getPiece().Alliance != this.Alliance)
                        legalMoves.add(new Move.attackMove(board,this,candidateDestinationTile.getPosition(),candidateDestinationTile.getPiece()));
                }
            }
        }
        return legalMoves;
    }

    @Override
    public Knight movePiece(Move move) {
        return new Knight(move.getMovedPiece().getPosition().getXCorr(),move.getMovedPiece().getPosition().getYCorr(),
                move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public String toString(){
        return PieceType.KNIGHT.toString();
    }
}
