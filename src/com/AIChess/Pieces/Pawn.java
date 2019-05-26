package com.AIChess.Pieces;

import com.AIChess.board.*;
import com.AIChess.board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent a pwan
 */
public class Pawn extends Piece{
    public Pawn(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance,PieceType.PAWN);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        int direction = 0;
        List<Move> legalMoves = new ArrayList<>();
        //check for direction of advancing.
        //if the piece is while, advancing in direction -1.
        //if the piece is black, advancing in direction 1.
        if (this.Alliance.isWhite())
            direction = -1;
        else
            direction = 1;
        //todo anpasant.
        //todo promotion.
        //check for attack moves.
        addAttackMoves(board,legalMoves,direction);

        //check for advance moves.
        addAdvanceMoves(board,legalMoves,direction);
        return legalMoves;
    }

    /**
     * check for advancing moves.
     * @param board - the game's board.
     * @param legalMoves - the legal moves list.
     * @param direction - the piece direction.
     */
    private void addAdvanceMoves(Board board,List<Move> legalMoves, int direction) {
        Position oneTile = new Position(this.getPosition().getXCorr() + direction,this.getPosition().getYCorr());
        boolean isOneTileFree = false;
        //check for advancing for 1 tile.
        if (oneTile.IsPositionLegal())
        {
            //check the destination tile is empty.
            if (!board.getTile(oneTile).isTileOccupied())
            {
                legalMoves.add(new Move.regularMove(board,this,oneTile));
                //update that one tile advance is free.
                isOneTileFree = true;
            }
        }
        //check if its first move and the first tile is free
        if (!this.firstMoveDone && isOneTileFree)
            //check the double advancing at the beginning of the game.
            checkTwoTilesMove(board,legalMoves,direction);
    }

    /**
     * check of advancing two tile at the first move of the game.
     * @param board - the game's board.
     * @param legalMoves - the legal moves list.
     * @param direction - the direction of the piece.
     */
    private void checkTwoTilesMove(Board board, List<Move> legalMoves, int direction) {
        Position twoTiles =  new Position(this.getPosition().getXCorr() + (2 * direction),this.getPosition().getYCorr());
        //the tile is legal because its a first move.
        //need to check that the tow tiles advance is empty.
            if (!board.getTile(twoTiles).isTileOccupied())
                legalMoves.add(new Move.regularMove(board,this,twoTiles));
    }

    /**
     * check for advancing moves.
     * @param board - the game's board.
     * @param legalMoves - the legal moves list.
     * @param direction - the direction of the piece.
     */
    private void addAttackMoves(Board board, List<Move> legalMoves, int direction) {
        Position firstCandidatePos = new Position(this.getPosition().getXCorr() + 1,this.getPosition().getYCorr() + direction);
        Position secondCandidatePos = new Position(this.getPosition().getXCorr() - 1,this.getPosition().getYCorr() + direction);
        //check first attack candidate.
        if (firstCandidatePos.IsPositionLegal())
            checkForOpp(board,legalMoves,firstCandidatePos);
        //chack second attack candidate.
        if (secondCandidatePos.IsPositionLegal())
            checkForOpp(board,legalMoves,secondCandidatePos);
    }

    /**
     * check for opponent piece at the given position
     * @param board - the game's board.
     * @param legalMoves - the legal moves list.
     * @param opponentCandidatePos - the given position.
     */
    private void checkForOpp(Board board, List<Move> legalMoves, Position opponentCandidatePos) {
        //check the tile is occupaied.
        if (board.getTile(opponentCandidatePos).isTileOccupied())
            //check if the alliances are different.
            if (board.getTile(opponentCandidatePos).getPiece().getPieceAlliance() != this.Alliance)
                legalMoves.add(new Move.attackMove(board,this,opponentCandidatePos,board.getTile(opponentCandidatePos).getPiece()));
    }


    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getMovedPiece().getPosition().getXCorr(),move.getMovedPiece().getPosition().getYCorr(),
                move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}
