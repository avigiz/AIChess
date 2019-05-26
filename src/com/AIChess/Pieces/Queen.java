package com.AIChess.Pieces;

import com.AIChess.board.*;
import com.AIChess.board.Board;

import java.util.ArrayList;
import java.util.List;


/**
 * this class represent a queen in a chess game.
 */
public class Queen extends Piece {
    public Queen(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance,PieceType.QUEEN);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        //get all 8 direction of the queen's moves.
        addMovesToDirection(board, legalMoves, 1, 1);
        addMovesToDirection(board, legalMoves, 1, -1);
        addMovesToDirection(board, legalMoves, -1, 1);
        addMovesToDirection(board, legalMoves, -1, -1);
        addMovesToDirection(board, legalMoves, 1, 0);
        addMovesToDirection(board, legalMoves, 0, -1);
        addMovesToDirection(board, legalMoves, 0, 1);
        addMovesToDirection(board, legalMoves, -1, 0);
        return legalMoves;
    }

    /**
     * adding moves of a certain direction of the queen.
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
            //check position is legal.
            if (newDestenation.IsPositionLegal())
            {
                Tile candidateDestinationTile = board.getTile(newDestenation);
                //check if tile is empty.
                if (!candidateDestinationTile.isTileOccupied())
                {
                    legalMoves.add(new Move.regularMove(board,this,candidateDestinationTile.getPosition()));
                }
                //tile is occupied.
                else
                {
                    //check if the piece on the tile is an enemy piece.
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
    public Queen movePiece(Move move) {
        return new Queen(move.getMovedPiece().getPosition().getXCorr(),move.getMovedPiece().getPosition().getYCorr(),
                move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }
}
