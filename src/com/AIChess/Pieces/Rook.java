package com.AIChess.Pieces;

import com.AIChess.board.*;
import com.AIChess.board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent a rook in a chess game.
 */
public class Rook extends Piece{
    public Rook(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance,PieceType.ROOK);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        addMovesToDirection(board, legalMoves, 1, 0);
        addMovesToDirection(board, legalMoves, 0, -1);
        addMovesToDirection(board, legalMoves, 0, 1);
        addMovesToDirection(board, legalMoves, -1, 0);
        return legalMoves;
    }

    /**
     * adding moves of a certain direction of the bishop.
     * @param board - the board's game.
     * @param legalMoves - the list of possible moves.
     * @param deltaX - the new direction in the X axis.
     * @param deltaY - thw new direction in the Y axis.
     */
    private void addMovesToDirection(Board board, List<Move> legalMoves, int deltaX, int deltaY) {
        Position newDestenation;
        boolean finish = false;
        while (!finish)
        {
            //new destination position.
            newDestenation = new Position(this.piecePosition.getXCorr() + deltaX, this.piecePosition.getYCorr() + deltaY);
            //check if position is legal.
            if (newDestenation.IsPositionLegal())
            {
                Tile candidateDestinationTile = board.getTile(newDestenation);
                //check the tile is empty.
                if (!candidateDestinationTile.isTileOccupied())
                {
                    legalMoves.add(new Move.regularMove(board,this,candidateDestinationTile.getPosition()));
                }
                //tile is occupied.
                else
                {
                    //check the piece is an enemy piece.
                    if (candidateDestinationTile.getPiece().Alliance != this.Alliance)
                        legalMoves.add(new Move.attackMove(board,this,candidateDestinationTile.getPosition(),candidateDestinationTile.getPiece()));
                    else
                        finish = true;
                }
            }
            //tile is not legal.
            else
            {
                finish = true;
            }
            //updating the delta for next candidate move.
            deltaX = deltaX + deltaX;
            deltaY = deltaY + deltaY;
        }
    }

    @Override
    public Rook movePiece(Move move) {
        return new Rook(move.getMovedPiece().getPosition().getXCorr(),move.getMovedPiece().getPosition().getYCorr(),
                move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }
}
