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

        //setting all the pieces of current player. except the moving piece.
        for (Piece piece : this.board.getCurrPlayer().getActivePieces()){
            if (!this.movedPiece.equals(piece)){
                builder.setPiece(piece);
            }
        }
        //setting all the pieces of the opponent player.
        for (Piece piece : this.board.getCurrPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
        }
        //setting the moving piece.
        builder.setPiece(this.movedPiece.movePiece(this));

        //change the move maker to the opponent.
        builder.setMoveMaker(this.board.getCurrPlayer().getOpponent().getAlliance());
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


    /**
     * this class represent an attack move.
     */
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


    /**
     * this class represent a regular move, not an attack move.
     */
    public static class regularMove extends Move {
        public regularMove(Board board, Piece piece, Position destinationPos) {
            super(board, piece, destinationPos);
        }

    }

    /**
     * this class represent pawn move.
     */
    public static class PawnMove extends Move {
        public PawnMove(Board board, Piece piece, Position destinationPos) {
            super(board, piece, destinationPos);
        }
    }

    /**
     * this class represent pawn attack move.
     */
    public class PawnAttackMove extends attackMove {
        public PawnAttackMove(Board board, Piece movedPiece, Position destinationPos, Piece attackedPiece) {
            super(board, movedPiece, destinationPos, attackedPiece);
        }
    }

    /**
     * this class represent a pawn EnPassant move.
     */
    public class PawnEnPassantAttackMove extends PawnAttackMove {
        public PawnEnPassantAttackMove(Board board, Piece movedPiece, Position destinationPos, Piece attackedPiece) {
            super(board, movedPiece, destinationPos, attackedPiece);
        }
    }

    /**
     * this class represent pawn jump of two tiles.
     */
    public static class PawnJump extends Move {
        public PawnJump(Board board, Piece piece, Position destinationPos) {
            super(board, piece, destinationPos);
        }

        /**
         * execute the jump move of a pawn.
         * @return - the new board after the move.
         */
        @Override
        public Board execute(){
            final Board.Builder builder = new Board.Builder();
            //setting all the pieces of current player. except the moving pawn.
            for (Piece piece : this.board.getCurrPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }

            //setting all the pieces of the opponent player.
            for (Piece piece : this.board.getCurrPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }

            Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
            //setting the moving piece.
            builder.setPiece(movedPawn);

            //setting the piece to be the EnPassant pawn for the opponent.
            builder.setEnPassantPawn(movedPawn);

            //change the move maker to the opponent.
            builder.setMoveMaker(board.getCurrPlayer().getOpponent().getAlliance());
            return builder.bulid();

        }
    }


    /**
     * the class represent a null move.
     * which is an invalid move.
     */
    public static class NullMove extends Move {
        public NullMove() {
            super(null, null, new Position(-1,-1));
        }

        @Override
        public Board execute(){
            throw new RuntimeException("Can not exeute the null move!");
        }
    }

    /**
     * this class represent a class that return all the exist move that possible.
     */
    public static class MoveFactory {

        private  MoveFactory(){
            throw new RuntimeException("Not instantiable!");
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
