package com.AIChess.player;

import com.AIChess.board.Board;
import com.AIChess.board.Move;

/**
 * this class represent the transition of executing a move in a chess game.
 */
public class MoveTransition {

    //the transition board.
    private Board transitionBoard;
    //the status of the transition.
    private MoveStatus moveStatus;
    //the move of the transition.
    private Move move;

    public MoveTransition (Board transitionBoard, MoveStatus moveStatus, Move move){
        this.move = move;
        this.moveStatus = moveStatus;
        this.transitionBoard = transitionBoard;
    }

    public Board getTransitionBoard() {
        return transitionBoard;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public Move getMove() {
        return move;
    }

}
