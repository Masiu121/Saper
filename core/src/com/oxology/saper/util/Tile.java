package com.oxology.saper.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    private int x, y;
    public TileType type;
    private float scale;
    public boolean bomb;
    public int bombsAround;
    public int flagsAround;

    public Tile(int x, int y, boolean bomb, float scale) {
        this.x = x;
        this.y = y;
        this.bomb = bomb;
        this.scale = scale;
        this.type = TileType.HIDDEN;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(type.getTexture(), this.x*32, this.y*32);
    }

    public void checkBombsAround(Tile[][] map) {
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if(x+i >= 0 && x+i < map.length && y+j >= 0 && y+j < map.length) {
                    if (map[x + i][y + j].bomb) {
                        bombsAround++;
                    }
                }
            }
        }
    }

    public void checkFlagsAround(Tile[][] map) {
        int flags = 0;
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if(x+i >= 0 && x+i < map.length && y+j >= 0 && y+j < map.length) {
                    if (map[x + i][y + j].type == TileType.FLAG) {
                        flags++;
                    }
                }
            }
        }
        flagsAround = flags;
    }

    public void reveal(Tile[][] map) {
        if(bomb) {
            this.type = TileType.BOMB;
        } else {
            if (bombsAround == 0) {
                this.type = TileType.EMPTY;
            } else {
                switch (bombsAround) {
                    case 1:
                        this.type = TileType.NUM_1;
                        break;
                    case 2:
                        this.type = TileType.NUM_2;
                        break;
                    case 3:
                        this.type = TileType.NUM_3;
                        break;
                    case 4:
                        this.type = TileType.NUM_4;
                        break;
                    case 5:
                        this.type = TileType.NUM_5;
                        break;
                    case 6:
                        this.type = TileType.NUM_6;
                        break;
                    case 7:
                        this.type = TileType.NUM_7;
                        break;
                    case 8:
                        this.type = TileType.NUM_8;
                        break;
                }
            }
        }

        if(bombsAround == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (x + i >= 0 && x + i < map.length && y + j >= 0 && y + j < map.length && map[x + i][y + j].type == TileType.HIDDEN) {
                        map[x + i][y + j].reveal(map);
                    }
                }
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void revealOthers(Tile[][] map) {
        if(bombsAround == flagsAround && type != TileType.HIDDEN) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (x + i >= 0 && x + i < map.length && y + j >= 0 && y + j < map.length && map[x + i][y + j].type == TileType.HIDDEN) {
                        map[x + i][y + j].reveal(map);
                    }
                }
            }
        }
        System.out.println(bombsAround == flagsAround);
    }
}
