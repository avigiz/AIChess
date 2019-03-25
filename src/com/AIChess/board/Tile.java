package com.AIChess.board;

import com.AIChess.Pieces.Piece;
import javafx.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * this class represent a tile in a chess board.
 */
public abstract class Tile {
    protected Position pos;

    private static final Map<Pair<Integer, Integer>, EmptyTile> Empty_Tiles_Cache = createAllPosibleEmptyTile();

    private static Map<Pair<Integer,Integer>,EmptyTile> createAllPosibleEmptyTile() {

        final Map<Pair<Integer, Integer>, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0 ; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                emptyTileMap.put(new Pair<Integer, Integer>(Integer.valueOf(i), Integer.valueOf(j)), new EmptyTile(i, j));
        }

        return Collections.unmodifiableMap(emptyTileMap);
    }

    public static Tile CreateTile (int CorrX,int CorrY, Piece piece){
        if (piece != null)
            return new OccupiedTile(CorrX,CorrY,piece);
        else
            return new EmptyTile(CorrX,CorrY);
    }

    public Tile (int x, int y){
        this.pos = new Position(x,y);
    }

    /**
     * check if a tile is occupied.
     * @return - true if the tile is occupied.
     */
    public abstract boolean isTileOccupied();

    /**
     * check for a piece on the tile
     * @return - the piece on the tile, if the tile is empty return null.
     */
    public abstract Piece getPiece();


}
