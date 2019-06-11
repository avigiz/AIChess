package com.AIChess;

import com.AIChess.player.BlackPlayer;
import com.AIChess.player.Player;
import com.AIChess.player.WhitePlayer;

/**
 * enum class represent the alliance of the player.
 */
public enum Alliance {
    WHTIE{
        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    },
    BLACK{
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };

    /**
     * abstract function that check if the player is the white player.
     * @return - true if the player is a white player.
     */
    public abstract boolean isWhite();

    /**
     * abstract function that check if the player is the black player.
     * @return - true if the player is a black player.
     */
    public abstract boolean isBlack();


    /**
     * abstract function sets the players in chess game.
     * @return - the chosen player.
     */
    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
