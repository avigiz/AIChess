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

/**
 * an abstract class that represent a player in the chess game.
 */
public abstract class Player {

    protected final Board board;
    protected final King playersKing;
    protected final List<Move> legalMoves;
    protected boolean isInCheck;


    public Player(Board board, List<Move> legalMove, List<Move> opponentLegalMove) {
        this.board = board;
        this.playersKing = establishKing();
        List<Move> castleMove = calcKingCastles(legalMove,opponentLegalMove);
        if (!castleMove.isEmpty()){
            legalMove.add((Move) castleMove);
        }
        this.legalMoves = legalMove;
        this.isInCheck = !Player.calcAttackOnTile(this.getPlayersKing().getPosition(),opponentLegalMove).isEmpty();
    }

    /**
     * this function calc the possible attack on a specific tile.
     * needed for the calc of the castling move in the sub classes.
     * @param position - the given position to check.
     * @param moves - the opponent list of moves.
     * @return - list of attacking move on the given position.
     */
    protected static List<Move> calcAttackOnTile(Position position, List<Move> moves) {
        List<Move> attackMoves = new ArrayList<>();
        for (Move move: moves) {
            if (position.equals(move.getDestinationPos())){
                attackMoves.add(move);
            }
        }
        return attackMoves;
    }

    /**
     * check there is a king of the player on the chess board.
     * @return - the king piece if founded.
     */
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

    /**
     * abstract function check of the posibilty of castling move of the player.
     * @param playerLegalMoves - the current player legal moves.
     * @param opponentLegalMoves - the opponent legal moves.
     * @return - list of moves (max of 2 castling moves).
     */
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

    /**
     * this function makes a move in a chess game.
     * @param move - the move to execute.
     * @return - the transition move after the move was execute.
     */
    public MoveTransition makeMove(Move move){
        //if illegal return new transition with illegal move.
        if (!isMoveLegal(move)){
            return new MoveTransition(this.board,MoveStatus.ILLEGAL,move);
        }


        Board transitionBoard = move.execute();
        //calc the attack on the king in the given transition board.
        List<Move> kingAttackes = Player.calcAttackOnTile(transitionBoard.getCurrPlayer().getOpponent().getPlayersKing().getPosition(),
                transitionBoard.getCurrPlayer().getLegalMove());
        //if the list is not empty the king is under atteck, the move leaves the player in check.
        if (!kingAttackes.isEmpty()){
            return new MoveTransition(this.board,MoveStatus.LEAVES_PLAYER_IN_CHECK,move);
        }
        //the move is legal, return with done.
        return new MoveTransition(transitionBoard,MoveStatus.DONE,move);
    }

    /**
     * check if there is a possible escape move for the king from the check.
     * @return - true if there is escape move from check.
     */
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
