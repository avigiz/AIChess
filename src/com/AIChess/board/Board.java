package com.AIChess.board;

import com.AIChess.Alliance;
import com.AIChess.Pieces.*;
import com.AIChess.player.BlackPlayer;
import com.AIChess.player.Player;
import com.AIChess.player.WhitePlayer;
import javafx.util.Pair;

import java.util.*;

/**
 * this class represent a board in chess game.
 */
public class Board {

    private final Tile[][] gameBoard;
    //pieces.
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;

    //players.
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private Player currPlayer;


    /**
         * contractor of the board, using the builder patten to create.
     * @param builder - the given builder whom build the board.
     */
    private Board (Builder builder){

        this.gameBoard = createGameBoard(builder);
        this.whitePieces = culcActivePieces(this.gameBoard,Alliance.WHTIE);
        this.blackPieces = culcActivePieces(this.gameBoard,Alliance.BLACK);

        final List<Move> whiteLegalMoves = culcLegalMoves(this.whitePieces);
        final List<Move> blackLegalMoves = culcLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this,whiteLegalMoves,blackLegalMoves);
        this.blackPlayer = new BlackPlayer(this,blackLegalMoves,whiteLegalMoves);
        this.currPlayer = builder.moveMaker.choosePlayer(this.whitePlayer,this.blackPlayer);

    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public Tile[][] getGameBoard() {
        return gameBoard;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    /**
     * over riding toString method.
     * @return - string of the current board.
     */
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


    /**
     * calculating all legal moves of the given pieces.
     * @param Pieces - the given pieces.
     * @return - list all legal moves of the pieces.
     */
    private List<Move> culcLegalMoves(List<Piece> Pieces) {
        List<Move> legalMoves = new ArrayList<>();
        for (Piece currPiece:Pieces) {
            legalMoves.addAll(currPiece.calculateLegalMoves(this));
        }
        return legalMoves;
    }

    /**
     * calculating the active pieces of an alliance.
     * @param gameBoard - the given board to check upon.
     * @param alliance - the given alliance to check upon.
     * @return - list of all the active pieces of the alliance.
     */
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

    /**
     * creating the board using the given builder to build.
     * @param builder - the given builder.
     * @return - new board.
     */
    private Tile[][] createGameBoard(Builder builder) {
        Tile[][] gameBoard = new Tile[boardUtils.numOfRow][boardUtils.numOfCol];
        for (int row = 0; row < boardUtils.numOfRow; row++){
            for (int col = 0; col < boardUtils.numOfCol; col++){
                gameBoard[row][col] = Tile.CreateTile(row,col,builder.boardCon.get(new Pair<>(row,col)));
            }
        }
        return gameBoard;
    }


    public Tile getTile(Position pos) {
        return gameBoard[pos.getXCorr()][pos.getYCorr()];
    }

    /**
     * init the starting position of a chess board.
     * @return - the starting position of a chess board.
     */
    public static Board initBoardStartPosition(){
        final Builder builder = new Builder();

        builder.setPiece(new Rook(0,0,Alliance.BLACK));
        builder.setPiece(new Knight(0,1,Alliance.BLACK));
        builder.setPiece(new Bishop(0,2,Alliance.BLACK));
        builder.setPiece(new Queen(0,3,Alliance.BLACK));
        builder.setPiece(new King(0,4,Alliance.BLACK));
        builder.setPiece(new Bishop(0,5,Alliance.BLACK));
        builder.setPiece(new Knight(0,6,Alliance.BLACK));
        builder.setPiece(new Rook(0,7,Alliance.BLACK));


        builder.setPiece(new Pawn(1,0,Alliance.BLACK));
        builder.setPiece(new Pawn(1,1,Alliance.BLACK));
        builder.setPiece(new Pawn(1,2,Alliance.BLACK));
        builder.setPiece(new Pawn(1,3,Alliance.BLACK));
        builder.setPiece(new Pawn(1,4,Alliance.BLACK));
        builder.setPiece(new Pawn(1,5,Alliance.BLACK));
        builder.setPiece(new Pawn(1,6,Alliance.BLACK));
        builder.setPiece(new Pawn(1,7,Alliance.BLACK));

        builder.setPiece(new Rook(7,0,Alliance.WHTIE));
        builder.setPiece(new Knight(7,1,Alliance.WHTIE));
        builder.setPiece(new Bishop(7,2,Alliance.WHTIE));
        builder.setPiece(new Queen(7,3,Alliance.WHTIE));
        builder.setPiece(new King(7,4,Alliance.WHTIE));
        builder.setPiece(new Bishop(7,5,Alliance.WHTIE));
        builder.setPiece(new Knight(7,6,Alliance.WHTIE));
        builder.setPiece(new Rook(7,7,Alliance.WHTIE));


        builder.setPiece(new Pawn(6,0,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,1,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,2,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,3,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,4,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,5,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,6,Alliance.WHTIE));
        builder.setPiece(new Pawn(6,7,Alliance.WHTIE));
        builder.moveMaker = Alliance.WHTIE;
        return builder.bulid();
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }


    /**
     * this class represent an inner class of the builder.
     * using the builder to create the beginning board and after every move.
     */
    public static class Builder {

        private Map<Pair<Integer,Integer>, Piece> boardCon;
        //save the current move maker.
        private Alliance moveMaker;

        private Pawn enPassantPwan;

        public Builder() {
            this.boardCon = new HashMap<>();
        }



        public Builder setPiece(Piece piece){
            this.boardCon.put(new Pair<>(piece.getPosition().getXCorr(),piece.getPosition().getYCorr()),piece);
            return this;
        }


        public Builder setNextMoveMaker(Alliance alliance){
            this.moveMaker = alliance;
            return this;
        }

        /**
         * main function which bulid the board using the builder.
         * @return
         */
        public Board bulid(){
            return new Board(this);
        }

        public void setEnPassantPawn(Pawn movedPawn) {
        }
    }

}
