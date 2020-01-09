package com.mygdx.sandbox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.sandbox.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

	//	config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		config.fullscreen = false;
		config.height= LwjglApplicationConfiguration.getDesktopDisplayMode().width - 150;
		config.width= LwjglApplicationConfiguration.getDesktopDisplayMode().height - 100;
		new LwjglApplication(new MyGame(), config);
	}
}
