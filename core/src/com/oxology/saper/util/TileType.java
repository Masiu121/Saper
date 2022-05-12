package com.oxology.saper.util;

import com.badlogic.gdx.graphics.Texture;

public enum TileType {
    HIDDEN(new Texture("Tiles/tile_hidden.png")),
    EMPTY(new Texture("Tiles/tile_empty.png")),
    BOMB(new Texture("Tiles/tile_bomb.png")),
    FLAG(new Texture("Tiles/tile_flag.png")),
    Q_MARK(new Texture("Tiles/tile_q_mark.png")),
    NUM_1(new Texture("Tiles/tile_num_1.png")),
    NUM_2(new Texture("Tiles/tile_num_2.png")),
    NUM_3(new Texture("Tiles/tile_num_3.png")),
    NUM_4(new Texture("Tiles/tile_num_4.png")),
    NUM_5(new Texture("Tiles/tile_num_5.png")),
    NUM_6(new Texture("Tiles/tile_num_6.png")),
    NUM_7(new Texture("Tiles/tile_num_7.png")),
    NUM_8(new Texture("Tiles/tile_num_8.png"));

    private Texture texture;

    TileType(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
