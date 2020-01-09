package com.mygdx.sandbox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

abstract public class GameObject {

    public float x, y;
    public float rotation;
    //public float scaleX, scaleY;
    public float width, height;
    public float speedX, speedY;
    public int lives;
    public int dmg;
    public Rectangle rectangle;
    Level lvl;

    public GameObject(Level level)
    {
        lvl=level;
        x=0;
        y=0;
        dmg=1;
        //scaleX=1;
        //scaleY=1;
        width=1;
        height=1;
        speedX=1;
        speedY=1;
        lives=1;
        rectangle=new Rectangle();
    }
    public GameObject()
    {
        x=0;
        y=0;
        dmg=1;
        //scaleX=1;
        //scaleY=1;
        width=1;
        height=1;
        speedX=1;
        speedY=1;
        lives=1;
        rectangle=new Rectangle();
    }
    public GameObject(float x,float y,float width,float height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        rectangle=new Rectangle();
    }
    public Rectangle getBounds(){
        rectangle.set(x,y,width,height);
        return rectangle;
    }

    public void drawDebug(ShapeRenderer shape)
    {
        shape.setColor(Color.RED);
        shape.rect(x,y,width,height);
    }

    abstract public void draw(SpriteBatch batch, float staTime);
    abstract public void update(float delta);



}
