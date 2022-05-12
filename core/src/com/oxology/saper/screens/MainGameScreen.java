package com.oxology.saper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.saper.SaperGame;
import com.oxology.saper.util.Difficulty;
import com.oxology.saper.util.Tile;

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
                    map[i][j] = new Tile(i, j, true, 1.0f);
                } else {
                    map[i][j] = new Tile(i, j, false, 1.0f);
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
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
                if(game.getGameX() > (map[i][j].getX()*16) && game.getGameX() < (map[i][j].getX()*16)+16) {
                    if(game.getGameY() > (map[i][j].getY()*16) && game.getGameY() < (map[i][j].getY()*16)+16) {
                        if(Gdx.input.justTouched()) {
                            map[i][j].reveal();
                        }
                    }
                }
            }
        }
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
