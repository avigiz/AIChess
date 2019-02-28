package com.AIChess.board;

import com.AIChess.Pieces.Piece;
import javafx.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
            return Empty_Tiles_Cache.get(new Position(CorrX,CorrY));
    }

    public Tile (int x, int y){
        this.pos = new Position(x,y);
    }

    public abstract boolean isTileOccupaied();

    public abstract Piece getPiece();

}
