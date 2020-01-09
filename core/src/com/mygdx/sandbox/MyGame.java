package com.mygdx.sandbox;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

public class MyGame extends Game {

    MainScreen mainScreen;
    GameScreen gameScreen;

    @Override
    public void create() {
        mainScreen = new MainScreen(this);
       // gameScreen = new GameScreen(this,1);

        if(Gdx.app.getType() == Application.ApplicationType.Android)
            System.out.println("Estoy en android");
        else if (Gdx.app.getType() == Application.ApplicationType.Desktop){

            if(Controllers.getControllers().size > 0){

            }
        }

        setScreen(mainScreen);
    }
}
