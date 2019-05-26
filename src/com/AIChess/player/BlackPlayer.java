package com.AIChess.player;

import com.AIChess.Alliance;
import com.AIChess.Pieces.Piece;
import com.AIChess.Pieces.Rook;
import com.AIChess.board.*;

import java.util.ArrayList;
import java.util.List;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board, List<Move> blackLegalMoves, List<Move> whiteLegalMoves) {
        super(board,blackLegalMoves,whiteLegalMoves);
    }

    @Override
    public List<Piece> getActivePieces() {
        return board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

    @Override
    public List<Move> calcKingCastles(List<Move> playerLegalMoves, List<Move> opponentLegalMoves) {

        List<Move> kingCastles = new ArrayList<>();

        if (!this.playersKing.getFirstMoveDone() && !this.isInCheck) {
            //black king side castle.
            if (!this.board.getTile(new Position(0, 6)).isTileOccupied() &&
                    !this.board.getTile(new Position(0, 5)).isTileOccupied()) {
                Tile rookTile = this.board.getTile(new Position(0, 7));
                if (rookTile.isTileOccupied() && !rookTile.getPiece().getFirstMoveDone()) {
                    if (Player.calcAttackOnTile(new Position(0, 6), opponentLegalMoves).isEmpty() &&
                            Player.calcAttackOnTile(new Position(0, 5), opponentLegalMoves).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new CastleMove.KingCastleMove(this.board,this.playersKing,new Position(0,6),(Rook)rookTile.getPiece(),
                                rookTile.getPosition(),new Position(0,6)));
                    }
                }
            }

            //black queen side castle.
            if (!this.board.getTile(new Position(0, 1)).isTileOccupied() &&
                    !this.board.getTile(new Position(0, 2)).isTileOccupied() &&
                    !this.board.getTile(new Position(0, 3)).isTileOccupied()) {

                Tile rookTile = this.board.getTile(new Position(0, 0));

                if (rookTile.isTileOccupied() && !rookTile.getPiece().getFirstMoveDone()) {
                    if (rookTile.isTileOccupied() && !rookTile.getPiece().getFirstMoveDone()) {
                        if (Player.calcAttackOnTile(new Position(0, 1), opponentLegalMoves).isEmpty() &&
                                Player.calcAttackOnTile(new Position(0, 2), opponentLegalMoves).isEmpty() &&
                                Player.calcAttackOnTile(new Position(0, 3), opponentLegalMoves).isEmpty() &&
                                rookTile.getPiece().getPieceType().isRook()) {
                            kingCastles.add(new CastleMove.QueenCastleMove(this.board,playersKing,new Position(0,2),(Rook)rookTile.getPiece(),
                                    rookTile.getPosition(),new Position(0,3)));
                        }
                    }
                }
            }
        }
        return null;
    }
}
