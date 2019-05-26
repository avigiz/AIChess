package com.AIChess.board;

import com.AIChess.Pieces.Pawn;
import com.AIChess.Pieces.Piece;


/**
 * this class represent an abstract class of a move in a chess game.
 */
public abstract class Move {

    public static final Move NullMove = new NullMove();

    final protected Board board;
    final protected Piece movedPiece;
    final protected Position destinationPos;

    public Move (Board board, Piece piece, Position destinationPos){
        this.board =board;
        this.movedPiece = piece;
        this.destinationPos = destinationPos;
    }

    public Position getDestinationPos() {
        return destinationPos;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public Board getBoard() {
        return board;
    }


    /**
     * main method, executing a move in a chess board.
     * executing a move which is not a castling move.
     * using a builder to execute the move.
     * @return - the board after executing the move.
     */
    public Board execute() {

        Board.Builder builder =  new Board.Builder();

        for (Piece piece : this.board.getCurrPlayer().getActivePieces()){
            if (!this.movedPiece.equals(piece)){
                builder.setPiece(piece);
            }
        }

        for (Piece piece : this.board.getCurrPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
        }

        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setNextMoveMaker(this.board.getCurrPlayer().getOpponent().getAlliance());
        return builder.bulid();
    }

    @Override
    public int hashCode(){
        int result = 31 + this.destinationPos.getXCorr() + this.destinationPos.getYCorr();
        result = result * 31 + this.movedPiece.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Move))
            return false;
        Move otherMove = (Move) other;
        return this.destinationPos.equals(otherMove.destinationPos) &&
               this.movedPiece.equals(otherMove.getMovedPiece());
    }

    public boolean isAttackMove(){
        return false;
    }

    public boolean isCastlingMove(){
        return false;
    }

    public Piece getAttackedPiece(){
        return null;
    }




    public static class attackMove extends Move {

        Piece attackedPiece;

        public attackMove(Board board, Piece piece, Position destinationPos, Piece attackedPiece) {
            super(board, piece, destinationPos);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public Piece getAttackedPiece() {
            return attackedPiece;
        }

        @Override
        public int hashCode(){
            return this.attackedPiece.hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(Object other){
            if (this == other)
                return true;
            if (!(other instanceof attackMove))
                return false;
            attackMove otherAttackMove = (attackMove) other;
            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
        }

        @Override
        public boolean isAttackMove(){
            return true;
        }

    }



    public static class regularMove extends Move {
        public regularMove(Board board, Piece piece, Position destinationPos) {
            super(board, piece, destinationPos);
        }

    }

    public static class PwanMove extends Move {
        public PwanMove(Board board, Piece piece, Position destinationPos) {
            super(board, piece, destinationPos);
        }
    }


    public class PwanAttackMove extends attackMove {
        public PwanAttackMove(Board board, Piece movedPiece, Position destinationPos, Piece attackedPiece) {
            super(board, movedPiece, destinationPos, attackedPiece);
        }
    }


    public class PwanEnPassantAttackMove extends PwanAttackMove {
        public PwanEnPassantAttackMove(Board board, Piece movedPiece, Position destinationPos, Piece attackedPiece) {
            super(board, movedPiece, destinationPos, attackedPiece);
        }
    }

    public static class PwanJump extends Move {
        public PwanJump(Board board, Piece piece, Position destinationPos) {
            super(board, piece, destinationPos);
        }

        @Override
        public Board execute(){
            final Board.Builder builder = new Board.Builder();
            for (Piece piece : this.board.getCurrPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (Piece piece : this.board.getCurrPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }

            Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setNextMoveMaker(board.getCurrPlayer().getOpponent().getAlliance());
            return builder.bulid();

        }
    }



    public static class NullMove extends Move {
        public NullMove() {
            super(null, null, new Position(-1,-1));
        }

        @Override
        public Board execute(){
            throw new RuntimeException("Can not exeute the null move!");
        }
    }


    public static class MoveFactory {

        private  MoveFactory(){
            throw new RuntimeException("Not instantiavle!");
        }

        public static Move createMove(Board board, Position currPos, Position destPos){
            for (Move move : board.getCurrPlayer().getLegalMove()){
                if (move.getMovedPiece().getPosition().equals(currPos) && move.getDestinationPos().equals(destPos))
                    return move;
            }
            return NullMove;
        }
    }



}
