package com.AIChess.player;

/**
 * this enum is for the moving status.
 * done represent a legal move.
 * illegal represent a illegal move.
 * leaves palyer in check represent illegal move that after executing the move the players king is in check,
 * which make the move as illegal.
 */
public enum MoveStatus {

    DONE{
        @Override
        boolean isDone() {
            return true;
        }

        @Override
        boolean isIllegal() {
            return false;
        }
    },
    ILLEGAL{
        @Override
        boolean isDone() {
            return false;
        }

        @Override
        boolean isIllegal() {
            return true;
        }
    },LEAVES_PLAYER_IN_CHECK {
        @Override
        boolean isDone() {
            return false;
        }

        @Override
        boolean isIllegal() {
            return false;
        }
    };




    abstract boolean isDone();

    abstract boolean isIllegal();
}
