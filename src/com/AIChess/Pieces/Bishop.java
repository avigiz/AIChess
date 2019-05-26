package com.AIChess.Pieces;

import com.AIChess.board.*;
import com.AIChess.board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent a bishop in a chess game.
 */
public class Bishop extends Piece {
    public Bishop(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance,PieceType.BISHOP);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        Position currPos = this.piecePosition;
        List<Move> legalMoves = new ArrayList<>();
        //check for all 4 diagonals of the bishop.
        addMovesToDirection(board, legalMoves, 1, 1);
        addMovesToDirection(board, legalMoves, 1, -1);
        addMovesToDirection(board, legalMoves, -1, 1);
        addMovesToDirection(board, legalMoves, -1, -1);
        return legalMoves;
    }

    @Override
    public Bishop movePiece(Move move) {
        return new Bishop(move.getMovedPiece().getPosition().getXCorr(),move.getMovedPiece().getPosition().getYCorr(),
                move.getMovedPiece().getPieceAlliance());
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
            //new position to check.
            newDestenation = new Position(this.getPosition().getXCorr() + deltaX, this.getPosition().getYCorr() + deltaY);
            //check if the new position is legal.
            if (newDestenation.IsPositionLegal())
            {
                //getting the destination tile.
                Tile candidateDestinationTile = board.getTile(newDestenation);
                //if its empty tile need to add the move to the moves list.
                if (!candidateDestinationTile.isTileOccupied())
                {
                    legalMoves.add(new Move.regularMove(board,this,candidateDestinationTile.getPosition()));
                }
                //if the tile is occupied, check if it's an enemy piece.
                else
                {
                    //add attack move.
                    if (candidateDestinationTile.getPiece().Alliance != this.Alliance)
                        legalMoves.add(new Move.attackMove(board,this,candidateDestinationTile.getPosition(),candidateDestinationTile.getPiece()));
                    //same alliance.
                    else
                        finish = true;
                }
            }
            //the tile isn't legal.
            else
            {
                finish = true;
            }
            //updating the new destination direction.
            deltaX = deltaX + deltaX;
            deltaY = deltaY + deltaY;
        }
    }

    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }
}
