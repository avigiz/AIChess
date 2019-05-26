package com.AIChess.player;

import com.AIChess.board.Board;
import com.AIChess.board.Move;

public class MoveTransition {

    private Board transitionBoard;
    private MoveStatus moveStatus;
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
