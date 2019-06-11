package com.AIChess.player;

import com.AIChess.Alliance;
import com.AIChess.Pieces.Piece;
import com.AIChess.Pieces.Rook;
import com.AIChess.board.*;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent the white player in the chess game.
 */
public class WhitePlayer extends Player{
    public WhitePlayer(Board board, List<Move> whiteLegalMoves, List<Move> blackLegalMoves) {
        super(board,whiteLegalMoves,blackLegalMoves);
    }

    @Override
    public List<Piece> getActivePieces() {
        return board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHTIE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    @Override
    public List<Move> calcKingCastles(List<Move> playerLegalMoves, List<Move> opponentLegalMoves) {

        List<Move> kingCastles = new ArrayList<>();

        if (!this.playersKing.getFirstMoveDone() && !this.isInCheck) {
            //whites king side castle.
            if (!this.board.getTile(new Position(7, 6)).isTileOccupied() &&
                    !this.board.getTile(new Position(7, 5)).isTileOccupied()) {
                Tile rookTile = this.board.getTile(new Position(7, 7));
                if (rookTile.isTileOccupied() && !rookTile.getPiece().getFirstMoveDone()) {
                    if (Player.calcAttackOnTile(new Position(7, 6), opponentLegalMoves).isEmpty() &&
                            Player.calcAttackOnTile(new Position(7, 5), opponentLegalMoves).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new CastleMove.KingCastleMove(this.board,this.playersKing,new Position(7,6),(Rook)rookTile.getPiece(),
                                rookTile.getPosition(),new Position(7,5)) {
                        });
                    }
                }
            }

            //whites queen side castle.
            if (!this.board.getTile(new Position(7, 1)).isTileOccupied() &&
                    !this.board.getTile(new Position(7, 2)).isTileOccupied() &&
                    !this.board.getTile(new Position(7, 3)).isTileOccupied()) {

                Tile rookTile = this.board.getTile(new Position(7, 0));

                if (rookTile.isTileOccupied() && !rookTile.getPiece().getFirstMoveDone()) {
                    if (rookTile.isTileOccupied() && !rookTile.getPiece().getFirstMoveDone()) {
                        if (Player.calcAttackOnTile(new Position(7, 1), opponentLegalMoves).isEmpty() &&
                                Player.calcAttackOnTile(new Position(7, 2), opponentLegalMoves).isEmpty() &&
                                Player.calcAttackOnTile(new Position(7, 3), opponentLegalMoves).isEmpty() &&
                                rookTile.getPiece().getPieceType().isRook()) {
                            kingCastles.add(new CastleMove.QueenCastleMove(this.board,this.playersKing,new Position(7,2),(Rook)rookTile.getPiece(),
                                    rookTile.getPosition(),new Position(7,3)));
                        }
                    }
                }
            }
        }
        return kingCastles;
    }
}
