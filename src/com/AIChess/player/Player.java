package com.AIChess.player;

import com.AIChess.Alliance;
import com.AIChess.Pieces.King;
import com.AIChess.Pieces.Piece;
import com.AIChess.board.Board;
import com.AIChess.board.Move;
import com.AIChess.board.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected final Board board;
    protected final King playersKing;
    protected final List<Move> legalMoves;
    protected boolean isInCheck;


    Player(Board board, List<Move> legalMove, List<Move> opponentLegalMove) {
        this.board = board;
        this.playersKing = establishKing();
        List<Move> castleMove = calcKingCastles(legalMove,opponentLegalMove);
        if (!castleMove.isEmpty()){
            legalMove.add((Move) castleMove);
        }
        this.legalMoves = legalMove;
        this.isInCheck = !Player.calcAttackOnTile(this.getPlayersKing().getPosition(),opponentLegalMove).isEmpty();
    }

    protected static List<Move> calcAttackOnTile(Position position, List<Move> moves) {
        List<Move> attackMoves = new ArrayList<>();
        for (Move move: moves) {
            if (position.equals(move.getDestinationPos())){
                attackMoves.add(move);
            }
        }
        return attackMoves;
    }

    protected  King establishKing(){
        for (Piece piece: getActivePieces()) {
            if (piece.getType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! , Not a valid board!");
    }

    public abstract List<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    public abstract  List<Move> calcKingCastles(List<Move> playerLegalMoves, List<Move> opponentLegalMoves);

    public boolean isMoveLegal(Move move){
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }

    public boolean isInCheckMate(){
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isStalemate(){
        return !this.isInCheck && ! hasEscapeMoves();
    }

    public boolean isCastle(){
        return false;
    }

    public King getPlayersKing() {
        return playersKing;
    }

    public List<Move> getLegalMove() {
        return legalMoves;
    }

    public MoveTransition makeMove(Move move){
        if (!isMoveLegal(move)){
            return new MoveTransition(this.board,MoveStatus.ILLEGAL,move);
        }

        Board transitionBoard = move.execute();
        List<Move> kingAttackes = Player.calcAttackOnTile(transitionBoard.getCurrPlayer().getOpponent().getPlayersKing().getPosition(),
                transitionBoard.getCurrPlayer().getLegalMove());

        if (!kingAttackes.isEmpty()){
            return new MoveTransition(this.board,MoveStatus.LEAVES_PLAYER_IN_CHECK,move);
        }
        return new MoveTransition(transitionBoard,MoveStatus.DONE,move);
    }

    protected boolean hasEscapeMoves(){
        for (Move move : this.legalMoves) {
            MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }
}
