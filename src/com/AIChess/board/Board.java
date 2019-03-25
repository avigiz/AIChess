package com.AIChess.board;

import com.AIChess.Alliance;
import com.AIChess.Pieces.*;
import com.AIChess.board.boardUtils;

import java.util.*;

/**
 * this class represent a board in chess game.
 */
public class Board {

    private final Tile[][] gameBoard;
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;

    private Board (Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = culcActivePieces(this.gameBoard,Alliance.WHTIE);
        this.blackPieces = culcActivePieces(this.gameBoard,Alliance.BLACK);

        final List<Move> whiteLegalMoves = culcLegalMoves(this.whitePieces);
        final List<Move> blackLegalMoves = culcLegalMoves(this.blackPieces);

    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < boardUtils.numOfCol; row++)
        {
            for (int col = 0; col < boardUtils.numOfCol; col++)
            {
                final String tileString = this.gameBoard[row][col].toString();
                stringBuilder.append(String.format("%3s", tileString));
                if (col == boardUtils.numOfCol - 1)
                    stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }


    private List<Move> culcLegalMoves(List<Piece> Pieces) {
        List<Move> legalMoves = new ArrayList<>();
        for (Piece currPiece:Pieces) {
            legalMoves.addAll(currPiece.calculateLegalMoves(this));
        }
        return legalMoves;
    }

    private List<Piece> culcActivePieces(Tile[][] gameBoard,Alliance alliance) {

        List<Piece> activePieces = new ArrayList<>();

        for (int row = 0; row < boardUtils.numOfRow; row++)
        {
            for (int col = 0; col < boardUtils.numOfCol; col++)
                if (gameBoard[row][col].isTileOccupied())
                    if (gameBoard[row][col].getPiece().getPieceAlliance() == alliance)
                        activePieces.add(gameBoard[row][col].getPiece());
        }
        return activePieces;
    }

    private Tile[][] createGameBoard(Builder builder) {
        Tile[][] gameBoard = new Tile[boardUtils.numOfRow][boardUtils.numOfCol];
        for (int row = 0; row < boardUtils.numOfRow; row++){
            for (int col = 0; col < boardUtils.numOfCol; col++){
                gameBoard[row][col] = Tile.CreateTile(row,col,builder.boardCon.get(new Position(row,col)));
            }
        }
        return gameBoard;
    }

    public Tile getTile(Position destinationPosition) {
        return gameBoard[destinationPosition.getXCorr()][destinationPosition.getYCorr()];
    }

    public static Board initBoardPosition(){
        final Builder builder = new Builder();
        builder.setPiece(new Rook(0,0,Alliance.WHTIE));
        builder.setPiece(new Knight(1,0,Alliance.WHTIE));
        builder.setPiece(new Bishop(2,0,Alliance.WHTIE));
        builder.setPiece(new Queen(3,0,Alliance.WHTIE));
        builder.setPiece(new King(4,0,Alliance.WHTIE));
        builder.setPiece(new Bishop(5,0,Alliance.WHTIE));
        builder.setPiece(new Knight(6,0,Alliance.WHTIE));
        builder.setPiece(new Rook(7,0,Alliance.WHTIE));


        builder.setPiece(new Pawn(0,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(1,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(2,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(3,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(4,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(5,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(7,1,Alliance.WHTIE));

        builder.setPiece(new Rook(0,8,Alliance.WHTIE));
        builder.setPiece(new Knight(1,8,Alliance.WHTIE));
        builder.setPiece(new Bishop(2,8,Alliance.WHTIE));
        builder.setPiece(new Queen(3,8,Alliance.WHTIE));
        builder.setPiece(new King(4,8,Alliance.WHTIE));
        builder.setPiece(new Bishop(5,8,Alliance.WHTIE));
        builder.setPiece(new Knight(6,8,Alliance.WHTIE));
        builder.setPiece(new Rook(7,8,Alliance.WHTIE));


        builder.setPiece(new Pawn(0,7,Alliance.WHTIE));
        builder.setPiece(new Pawn(1,7,Alliance.WHTIE));
        builder.setPiece(new Pawn(2,7,Alliance.WHTIE));
        builder.setPiece(new Pawn(3,7,Alliance.WHTIE));
        builder.setPiece(new Pawn(4,7,Alliance.WHTIE));
        builder.setPiece(new Pawn(5,7,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,7,Alliance.WHTIE));
        builder.setPiece(new Pawn(7,7,Alliance.WHTIE));

        builder.moveMaker = Alliance.WHTIE;
        return builder.bulid();
    }



    public static class Builder{

        private Map<Position, Piece> boardCon;
        private Alliance moveMaker;

        public Builder(){
            this.boardCon = new HashMap<>();
        }

        public Builder setPiece(Piece piece){
            this.boardCon.put(piece.getPosition(),piece);
            return this;
        }


        public Builder setMoveOnThisBoardMaker(Alliance alliance){
            this.moveMaker = alliance;
            return this;
        }
        public Board bulid(){
            return new Board(this);
        }
    }

}
