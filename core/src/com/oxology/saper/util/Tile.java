package com.oxology.saper.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    private int x, y;
    private TileType type;
    private float scale;
    private boolean bomb;

    public Tile(int x, int y, boolean bomb, float scale) {
        this.x = x;
        this.y = y;
        this.bomb = bomb;
        this.scale = scale;
        this.type = TileType.HIDDEN;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(type.getTexture(), this.x*16, this.y*16);
    }

    public void reveal() {
        if(bomb)
            this.type = TileType.BOMB;
        else
            this.type = TileType.EMPTY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
