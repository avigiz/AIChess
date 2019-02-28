package com.AIChess.Pieces;

import com.AIChess.Alliance;
import com.AIChess.board.Board;
import com.AIChess.board.Move;
import com.AIChess.board.Position;
import com.AIChess.board.Tile;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(int pieceXCorr, int pieceYCorr, com.AIChess.Alliance alliance) {
        super(pieceXCorr, pieceYCorr, alliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        Position curr = this.getPosition();
        Position destinationPosition;

        List<Move> legalMoves = new ArrayList<>();
        ArrayList<Pair<Integer,Integer>> allCandidatesPosition = getAllKnightCandidate(curr);

        for (Pair<Integer,Integer> CandidateMove : allCandidatesPosition) {
            destinationPosition = new Position(CandidateMove.getKey(),CandidateMove.getValue());
            if (destinationPosition.IsPositionLegal())
            {
                Tile candidateDestinationTile = board.getTile(destinationPosition);
                if (!candidateDestinationTile.isTileOccupaied())
                    legalMoves.add(new Move());
                else
                {
                    Piece pieceAtPosition = candidateDestinationTile.getPiece();
                    Alliance pieceAllaince = pieceAtPosition.getPieceAlliance();

                    if (pieceAllaince != this.getPieceAlliance())
                        legalMoves.add(new Move());
                }
            }
        }
        return legalMoves;
    }

    private ArrayList<Pair<Integer,Integer>> getAllKnightCandidate(Position curr) {
        ArrayList ans = new ArrayList();
        ans.add(curr.getXCorr() + 1, curr.getYCorr() + 2);
        ans.add(curr.getXCorr() + 1, curr.getYCorr() - 2);

        ans.add(curr.getXCorr() - 1, curr.getYCorr() + 2);
        ans.add(curr.getXCorr() - 1, curr.getYCorr() - 2);

        ans.add(curr.getXCorr() + 2, curr.getYCorr() - 1);
        ans.add(curr.getXCorr() + 2, curr.getYCorr() + 1);

        ans.add(curr.getXCorr() - 2, curr.getYCorr() - 1);
        ans.add(curr.getXCorr() - 2, curr.getYCorr() + 1);

        return ans;
    }
}
