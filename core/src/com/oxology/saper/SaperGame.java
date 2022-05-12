package com.oxology.saper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.saper.screens.MainGameScreen;
import com.oxology.saper.util.Difficulty;

public class SaperGame extends Game {
	private SpriteBatch batch;
	public int gameViewportWidth;
	public int gameViewportHeight;
	public float gameViewportXProp;
	public float gameViewportYProp;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameViewportWidth = 800;
		gameViewportHeight = 450;
		setup();
		this.setScreen(new MainGameScreen(this, Difficulty.EASY));
	}

	public void setup() {
		gameViewportXProp = (float) Gdx.graphics.getBackBufferWidth() / gameViewportWidth;
		gameViewportYProp = (float) Gdx.graphics.getBackBufferHeight() / gameViewportHeight;
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyJustPressed(Input.Keys.C))
			Gdx.app.exit();

		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public float getGameX() {
		return Gdx.input.getX()/ gameViewportXProp;
	}

	public float getGameY() {
		return gameViewportHeight-Gdx.input.getY()/ gameViewportYProp;
	}
}
