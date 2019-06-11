package com.AIChess.Pieces;

/**
 * enum of the pieces type.
 */
public enum PieceType {

    PAWN("P"){
        public boolean isKing(){
            return false;
        }

        public boolean isRook(){
            return false;
        }
    },

    KNIGHT("N"){
        public boolean isKing(){
            return false;
        }

        public boolean isRook(){
            return false;
        }
    },
    BISHOP("B"){
        public boolean isKing(){
            return false;
        }

        public boolean isRook(){
            return false;
        }
    },
    ROOK("R"){
        public boolean isKing(){
            return false;
        }

        public boolean isRook(){
            return true;
        }
    },
    KING("K"){
        public boolean isKing(){
            return true;
        }

        public boolean isRook(){
            return false;
        }
    },
    QUEEN("Q"){
        public boolean isKing(){
            return false;
        }

        public boolean isRook(){
            return false;
        }
    };

    private String pieceType;

    PieceType (String pieceType){
        this.pieceType = pieceType;
    }

    @Override
    public String toString(){
        return this.pieceType;
    }

    /**
     * abstract function that check if a piece is a king.
     * @return - true if the piece is a king.
     */
    public abstract boolean isKing();

    /**
     * abstract function that check if a piece is a rook.
     * @return - true if the piece is a rook.
     */
    public abstract boolean isRook();
}
