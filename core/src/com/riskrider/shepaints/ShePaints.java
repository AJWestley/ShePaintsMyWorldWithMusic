package com.riskrider.shepaints;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.riskrider.shepaints.screens.TestScreen;
import com.riskrider.shepaints.tools.GameCamera;
import com.riskrider.shepaints.tools.MessageDisplay;

public class ShePaints extends Game {

	public static final int WIDTH = 1080;
	public static final int HEIGHT = 640;
	public static boolean IS_MOBILE = true;

	public SpriteBatch batch;
	public GameCamera camera;

	public MessageDisplay messageDisplay;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		camera = new GameCamera(WIDTH, HEIGHT);
		messageDisplay = new MessageDisplay();
		if (Gdx.app.getType() == Application.ApplicationType.Android) IS_MOBILE = true;
		this.setScreen(new TestScreen(this));

	}

	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined());
		super.render();
	}

	@Override
	public void resize (int width, int height) {
		camera.update(width, height);
		super.resize(width, height);
	}
}
