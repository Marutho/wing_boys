package com.mygdx.sandbox;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {

    String TAG_LIFECYCLE = "LIFECYCLE";
    public WorldController controller;
    public WorldRenderer renderer;
    public InputHelper iProcessor;
    public Screen screen;
    public MyGame gameMain;
    public Level level;
    public int numPlayers;
    public int lives;

    public GameScreen(MyGame gameMain, int numPlayers, int lives) {
        this.gameMain= gameMain;
        this.numPlayers = numPlayers;
        this.lives=lives;
    }

    @Override
    public void show() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.debug(TAG_LIFECYCLE, "Created");
        CameraHelper helper = new CameraHelper();
        level = new Level(numPlayers,helper,lives);
        controller = new WorldController(level);
        iProcessor = new InputHelper(this);
        Gdx.input.setInputProcessor(iProcessor);
        renderer = new WorldRenderer(controller);

        Controllers c;
    }

    @Override
    public void render(float delta) {
        controller.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        iProcessor.Update();

    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width,height);
        Gdx.app.debug(TAG_LIFECYCLE, "Resized to: "+ height + "x" + width);
        level.hudCamera.viewportWidth = width;
        level.hudCamera.viewportHeight = height;
        //0,0 in the lower left corner
        level.hudCamera.position.x = width/2;
        level.hudCamera.position.y = height/4;
        level.hudCamera.update();


    }

    @Override
    public void pause() {
        Gdx.app.debug(TAG_LIFECYCLE, "Paused");
    }

    @Override
    public void resume() {
        Gdx.app.debug(TAG_LIFECYCLE, "Resumed");
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Gdx.app.debug(TAG_LIFECYCLE, "Disposed");
    }
}
