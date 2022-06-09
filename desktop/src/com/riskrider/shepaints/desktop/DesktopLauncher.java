package com.riskrider.shepaints.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.riskrider.shepaints.ShePaints;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.backgroundFPS = 30;
		config.resizable = true;
		config.width = ShePaints.WIDTH;
		config.height = ShePaints.HEIGHT;
		config.title = "She Paints My World With Music";
		new LwjglApplication(new ShePaints(), config);
	}
}
