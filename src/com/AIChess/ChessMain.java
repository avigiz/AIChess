package com.AIChess;

import com.AIChess.board.Board;

public class ChessMain {

    public static void main(String[] args){
        Board board = Board.initBoardStartPosition();

        System.out.println(board);
    }
}
