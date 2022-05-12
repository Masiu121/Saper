package com.oxology.saper.util;

import com.badlogic.gdx.graphics.Texture;

public enum TileType {
    HIDDEN(new Texture("Tiles/tile_hidden.png")),
    EMPTY(new Texture("Tiles/tile_empty.png")),
    BOMB(new Texture("Tiles/tile_bomb.png"));
//    FLAG(new Texture("")),
//    Q_MARK(new Texture("")),
//    NUM_1(new Texture("")),
//    NUM_2(new Texture("")),
//    NUM_3(new Texture("")),
//    NUM_4(new Texture("")),
//    NUM_5(new Texture("")),
//    NUM_6(new Texture("")),
//    NUM_7(new Texture("")),
//    NUM_8(new Texture(""));

    private Texture texture;

    TileType(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
