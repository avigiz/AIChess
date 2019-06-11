package com.AIChess.board;

import com.AIChess.Pieces.Piece;
import com.AIChess.Pieces.Rook;

/**
 * this class represent an abstract castling move.
 * this class has two sub classes of king side casting and queen side castling.
 */
public abstract class CastleMove extends Move {
    //the rook of the castle move.
    protected Rook castleRook;

    protected Position castleRookStartPos;
    protected Position castleRookDestPos;

    public CastleMove(Board board, Piece piece, Position destinationPos, Rook rook, Position castleRookStartPos, Position castleRookDestPos) {
        super(board, piece, destinationPos);
        this.castleRook = rook;
        this.castleRookStartPos = castleRookStartPos;
        this.castleRookDestPos = castleRookDestPos;
    }

    public Rook getCastleRook(){
        return this.castleRook;
    }

    @Override
    public boolean isCastlingMove(){
        return true;
    }

    /**
     * main method that execute a castling move over the board.
     * using the builder to set the pieces.
     * @return - the new board after executing the castling move.
     */
    @Override
    public Board execute(){
        Board.Builder builder =  new Board.Builder();

        //setting all the pieces of current player. except the moving piece - King.
        for (Piece piece : this.board.getCurrPlayer().getActivePieces()){
            if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)){
                builder.setPiece(piece);
            }
        }

        //setting all the pieces of the opponent player.
        for (Piece piece : this.board.getCurrPlayer().getOpponent().getActivePieces()){
            builder.setPiece(piece);
        }

        //setting the moving piece - the King.
        builder.setPiece(this.movedPiece.movePiece(this));

        //setting the rook to the new position.
        builder.setPiece(new Rook(this.castleRook.getPosition().getXCorr(),this.castleRook.getPosition().getYCorr()
                ,this.castleRook.getPieceAlliance()));

        //change the move maker to the opponent.
        builder.setMoveMaker(this.board.getCurrPlayer().getOpponent().getAlliance());
        return builder.bulid();
    }


    /**
     * this class represent a king side castling.
     */
    public static class KingCastleMove extends CastleMove {
        public KingCastleMove(Board board, Piece piece, Position destinationPos, Rook rook, Position castleRookStartPos, Position castleRookDestPos) {
            super(board, piece, destinationPos,rook,castleRookStartPos,castleRookDestPos);
        }

        /**
         * over riding the to string method.
         * @return -  a string represent king castling in chess.
         */
        @Override
        public String toString(){
            return "0-0";
        }
    }

    /**
     * this class represent a queen side castling.
     */
    public static class QueenCastleMove extends CastleMove {
        public QueenCastleMove(Board board, Piece piece, Position destinationPos, Rook rook, Position castleRookStartPos, Position castleRookDestPos) {
            super(board, piece, destinationPos,rook,castleRookStartPos,castleRookDestPos);
        }
        /**
         * over riding the to string method.
         * @return -  a string represent queen castling in chess.
         */
        @Override
        public String toString(){
            return "0-0-0";
        }
    }




}
