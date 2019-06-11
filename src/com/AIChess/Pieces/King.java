package com.AIChess.Pieces;

import com.AIChess.board.*;
import com.AIChess.board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent the king piece in chess games.
 */
public class King extends Piece {
    public King(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance,PieceType.KING);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        List<Position> allPossiableMoves = new ArrayList<>();

        //check all 8 directions of the king possible moves.
        allPossiableMoves.add(new Position(1,1));
        allPossiableMoves.add(new Position(1,-1));
        allPossiableMoves.add(new Position(-1,1));
        allPossiableMoves.add(new Position(-1,-1));
        allPossiableMoves.add(new Position(0,0));
        allPossiableMoves.add(new Position(0,1));
        allPossiableMoves.add(new Position(1,0));
        allPossiableMoves.add(new Position(1,1));

        Position newDestenation;
        for (Position candidateTile: allPossiableMoves) {
            newDestenation = new Position(candidateTile.getXCorr() + piecePosition.getXCorr(),candidateTile.getYCorr() + piecePosition.getYCorr());
            //check the tile is legal.
            if (newDestenation.IsPositionLegal())
            {
                Tile candidateDestinationTile = board.getTile(newDestenation);
                //check the tile is an empty tile.
                if (!candidateDestinationTile.isTileOccupied())
                {
                    legalMoves.add(new Move.regularMove(board,this,candidateDestinationTile.getPosition()));
                }
                //check if the piece on the destanation tile is enemy piece.
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
    public King movePiece(Move move) {
        return new King(move.getMovedPiece().getPosition().getXCorr(),move.getMovedPiece().getPosition().getYCorr(),
                move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
}
