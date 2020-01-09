package com.mygdx.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorldController extends InputAdapter {

    public Sprite spr;
 //   public Sprite[] sprites;
    public String TAG_KEYS = "KEYS";
    public int selectedSprite;
    public ArrayList<GameObject> objects;
    public CameraHelper helper;
    public Level level;
    public Background background;


    public WorldController(Level level){
        Gdx.input.setInputProcessor(this);
        //objects = new ArrayList<GameObject>();
        this.level=level;
        this.helper=this.level.helper;

        ArcadeHandler arcade = new ArcadeHandler(this.level);
        Controllers.addListener(arcade);
        init();

    }

    public void init()
    {

    }

    public void update(float delta){

        level.update(delta);
        helper.followGO(level.player, level.bg);
        helper.moveCamera(delta);

    }

    @Override
    public boolean keyDown (int keycode){
        //Gdx.app.debug(TAG_KEYS, keycode+" has been pressed");
        if(keycode==Input.Keys.I)
        {
            Gdx.app.debug("SPRITE", spr.getBoundingRectangle()+"");
        }
        return false;
    }
}
