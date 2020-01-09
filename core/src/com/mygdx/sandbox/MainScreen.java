package com.mygdx.sandbox;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.mygdx.sandbox.HUD.HUD;
import com.mygdx.sandbox.HUD.TextButton;

public class MainScreen implements Screen {

    MyGame game;
    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    public InputMenu iProcessor;
    public TextButton b1;
    public TextButton b2;
    public BackgroundMenu bg;

    //HUD THINGS
    public OrthographicCamera hudCamera;
    HUD hud;

    public MainScreen(final MyGame game) {

        bg = GOFactory.generateBRMenu(-Gdx.graphics.getWidth()/2,-Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        SoundManager.reproduceMenuMusic();
        this.game = game;
        font = new BitmapFont();
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //HUD THINGS
        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        if( Controllers.getControllers().size > 0)
            Controllers.addListener(new ControllerListener() {
                @Override
                public void connected(Controller controller) {

                }


                @Override
                public void disconnected(Controller controller) {

                }

                @Override
                public boolean buttonDown(Controller controller, int buttonCode) {

                    //CONTROLLER 1
                    if(Controllers.getControllers().indexOf(controller,true) == 0) {

                        if(buttonCode == 6)
                            game.setScreen(game.gameScreen=new GameScreen(game,1,15));

                            if(buttonCode == 9)
                                Gdx.app.exit();

                        //man.switchColor(buttonCode);
                        return true;
                    }

                    //CONTROLLER 2
                    if(Controllers.getControllers().indexOf(controller,true) == 1) {

                        if(buttonCode == 6)
                            game.setScreen(game.gameScreen=new GameScreen(game,2,15));

                            if(buttonCode == 9)
                                Gdx.app.exit();

                        //man.switchColor(buttonCode);
                        return true;
                    }

                    return false;
                }

                @Override
                public boolean buttonUp(Controller controller, int buttonCode) {
                    return false;
                }

                @Override
                public boolean axisMoved(Controller controller, int axisCode, float value) {
                    return false;
                }



                @Override
                public boolean povMoved(Controller controller, int povCode, PovDirection value) {
                    return false;
                }

                @Override
                public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
                    return false;
                }

                @Override
                public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
                    return false;
                }


                @Override
                public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
                    return false;
                }
            });
        hud = new HUD();
        b1 = new TextButton("START 1 PLAYER",Gdx.graphics.getWidth()/2.8f,Gdx.graphics.getHeight()/4,320,70)
        {
            @Override
            public void click() {
                game.setScreen(game.gameScreen=new GameScreen(game,1,15));
            }
        };

        if (Gdx.app.getType() == Application.ApplicationType.Desktop)
        {
            b2 = new TextButton("START 2 PLAYER",Gdx.graphics.getWidth()/2.8f,Gdx.graphics.getHeight()/6,320,70)
            {
                @Override
                public void click() {
                    game.setScreen(game.gameScreen=new GameScreen(game,2,15));
                }
            };
            hud.add(b2);
        }else{
            b2 = new TextButton("START HARD MODE",Gdx.graphics.getWidth()/2.8f,Gdx.graphics.getHeight()/6,320,70)
            {
                @Override
                public void click() {
                    game.setScreen(game.gameScreen=new GameScreen(game,2, 5));
                }
            };
            hud.add(b2);
        }


        hud.add(b1);


        //
    }

    @Override
    public void show() {
        iProcessor = new InputMenu(this);
        Gdx.input.setInputProcessor(iProcessor);
    }

    @Override
    public void render(float delta) {


        //render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bg.draw(batch, delta);


        //font.draw(batch,"Click Anywhere to start",-camera.viewportWidth/2,0,camera.viewportWidth, Align.center,true);
        font.draw(batch,"WELCOME, CLICK START TO START",-camera.viewportWidth/2.1F,camera.viewportHeight/8,camera.viewportWidth, Align.center,true);

        batch.end();



        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        hud.render(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();

        hudCamera.viewportWidth = width;
        hudCamera.viewportHeight = height;
        //0,0 in the lower left corner
        hudCamera.position.x = width/2;
        hudCamera.position.y = height/4;
        hudCamera.update();
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
        batch.dispose();
        font.dispose();
    }
}
