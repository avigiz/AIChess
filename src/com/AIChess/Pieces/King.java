package com.AIChess.Pieces;

import com.AIChess.Alliance;
import com.AIChess.board.Board;
import com.AIChess.board.Move;
import com.AIChess.board.Position;
import com.AIChess.board.Tile;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent the king piece in chess games.
 */
public class King extends Piece {
    public King(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        List<Position> allPossiableMoves = new ArrayList<>();

        //todo castling.
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
                    legalMoves.add(new Move());
                }
                //check if the piece on the destanation tile is enemy piece.
                else
                {
                    if (candidateDestinationTile.getPiece().Alliance != this.Alliance)
                        legalMoves.add(new Move());
                }
            }
        }
        return legalMoves;
    }

    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
}
