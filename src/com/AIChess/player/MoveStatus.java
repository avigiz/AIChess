package com.AIChess.player;

public enum MoveStatus {

    DONE{
        @Override
        boolean isDone() {
            return true;
        }

        @Override
        boolean isIllega() {
            return false;
        }
    },
    ILLEGAL{
        @Override
        boolean isDone() {
            return false;
        }

        @Override
        boolean isIllega() {
            return true;
        }
    },LEAVES_PLAYER_IN_CHECK {
        @Override
        boolean isDone() {
            return false;
        }

        @Override
        boolean isIllega() {
            return false;
        }
    };




    abstract boolean isDone();

    abstract boolean isIllega();
}
