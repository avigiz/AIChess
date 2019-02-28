package com.AIChess.Pieces;

import com.AIChess.Alliance;
import com.AIChess.board.Board;
import com.AIChess.board.Move;
import com.AIChess.board.Position;
import com.AIChess.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        Position currPos = this.piecePosition;
        List<Move> legalMoves = new ArrayList<>();
        addMovesForDirection(board, legalMoves, currPos, 1, 1);
        addMovesForDirection(board, legalMoves, currPos, 1, -1);
        addMovesForDirection(board, legalMoves, currPos, -1, 1);
        addMovesForDirection(board, legalMoves, currPos, -1, -1);
        return legalMoves;
    }

    private void addMovesForDirection(Board board, List<Move> legalMoves, Position currPos, int deltaX, int deltaY) {
        Position newDestenation = new Position(currPos.getXCorr() + deltaX, currPos.getYCorr() + deltaY);
        boolean finish = false;
        while (!finish)
        {
            if (newDestenation.IsPositionLegal())
            {
                Tile candidateDestinationTile = board.getTile(newDestenation);
                if (!candidateDestinationTile.isTileOccupaied())
                {
                    legalMoves.add(new Move());
                }
                else
                {
                    Piece pieceAtPosition = candidateDestinationTile.getPiece();
                    Alliance pieceAllaince = pieceAtPosition.getPieceAlliance();
                    if (pieceAllaince != this.getPieceAlliance())
                        legalMoves.add(new Move());
                    else
                        finish = true;
                }
            }
            else
            {
                finish = true;
            }
            deltaX = deltaX + deltaX;
            deltaY = deltaY + deltaY;
        }
    }
}
