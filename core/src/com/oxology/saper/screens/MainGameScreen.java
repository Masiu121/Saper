package com.oxology.saper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.saper.SaperGame;
import com.oxology.saper.util.Difficulty;
import com.oxology.saper.util.Tile;
import com.oxology.saper.util.TileType;

import java.util.Random;

public class MainGameScreen implements Screen {
    private SaperGame game;
    private Difficulty difficulty;
    private Tile[][] map;

    private OrthographicCamera camera;

    public MainGameScreen(SaperGame game, Difficulty difficulty) {
        this.camera = new OrthographicCamera(game.gameViewportWidth, game.gameViewportHeight);
        this.camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0.0f);
        camera.update();

        this.game = game;
        this.difficulty = difficulty;
        Random random = new Random();
        map = new Tile[difficulty.getRows()][difficulty.getCols()];
        for(int i = 0; i < difficulty.getRows(); i++) {
            for(int j = 0; j < difficulty.getCols(); j++) {
                if(random.nextFloat()<difficulty.getBombChance()) {
                    map[i][j] = new Tile(i, j, true, 0.5f);
                } else {
                    map[i][j] = new Tile(i, j, false, 0.5f);
                }
            }
        }

        for(int i = 0; i < difficulty.getRows(); i++) {
            for(int j = 0; j < difficulty.getCols(); j++) {
                map[i][j].checkBombsAround(map);
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1);
        game.getBatch().setProjectionMatrix(camera.combined);
        camera.update();

        update();

        game.getBatch().begin();
        for(int i = 0; i < difficulty.getRows(); i++) {
            for(int j = 0; j < difficulty.getCols(); j++) {
                map[i][j].draw(game.getBatch());
            }
        }
        game.getBatch().end();
    }

    private void update() {
        for(int i = 0; i < difficulty.getRows(); i++) {
            for(int j = 0; j < difficulty.getCols(); j++) {
                if(game.getGameX() > (map[i][j].getX()*32) && game.getGameX() < (map[i][j].getX()*32)+32) {
                    if(game.getGameY() > (map[i][j].getY()*32) && game.getGameY() < (map[i][j].getY()*32)+32) {
                        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                            if(map[i][j].type == TileType.HIDDEN)
                                if(map[i][j].bomb)
                                    revealBombs();
                                else
                                    map[i][j].reveal(map);
                            else
                                map[i][j].revealOthers(map);
                        }
                        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
                            if(map[i][j].type == TileType.HIDDEN)
                                map[i][j].type = TileType.FLAG;
                            else if(map[i][j].type == TileType.FLAG)
                                map[i][j].type = TileType.Q_MARK;
                            else if(map[i][j].type == TileType.Q_MARK)
                                map[i][j].type = TileType.HIDDEN;

                            for(int i1 = 0; i1 < difficulty.getRows(); i1++) {
                                for(int j1 = 0; j1 < difficulty.getCols(); j1++) {
                                    map[i1][j1].checkFlagsAround(map);
                                }
                            }
                            System.out.println(map[i][j].flagsAround + ", " + map[i][j].bombsAround);
                        }
                    }
                }
            }
        }
    }

    private void revealBombs() {
        for(int i = 0; i < difficulty.getRows(); i++) {
            for(int j = 0; j < difficulty.getCols(); j++) {
                if(map[i][j].bomb) {
                    map[i][j].type = TileType.BOMB;
                }
            }
        }
    }

    private boolean checkWin() {
        for(int i = 0; i < difficulty.getRows(); i++) {
            for(int j = 0; j < difficulty.getCols(); j++) {
                if(map[i][j].bomb && map[i][j].type != TileType.FLAG) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void resize(int width, int height) {
        game.setup();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
