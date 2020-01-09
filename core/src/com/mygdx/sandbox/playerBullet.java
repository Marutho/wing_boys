package com.mygdx.sandbox;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class playerBullet extends GameObject {

    int direction;

    public playerBullet(float posX, float posY, int direction, int rotation){
        super(null);
        x = posX;
        y = posY;

        this.direction=direction;
        this.rotation = rotation;

        //width = Assets.getInstance().textureRegions[0].getRegionWidth();
        //height = Assets.getInstance(.textureRegions[0].getRegionHeight();

        width = 1.5f;
        height = 1.5f;

        //scaleX = 1.0f;
        //scaleY = 1.0f;

        speedX = 3.0f;
        speedY = 3.0f;
    }
    public playerBullet(float posX, float posY, float width, float height, int lives, int dmg, int direction, int rotation){
        super(null);
        x = posX;
        y = posY;

        this.direction=direction;
        this.rotation = rotation;

        //width = Assets.getInstance().textureRegions[0].getRegionWidth();
        //height = Assets.getInstance(.textureRegions[0].getRegionHeight();

        this.width = width;
        this.height = height;
        this.lives = lives;
        this.dmg = dmg;

        //scaleX = 1.0f;
        //scaleY = 1.0f;

        speedX = 6.0f;
        speedY = 6.0f;
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {
       // batch.draw(Assets.getInstance().bulletTR,x,y,0,0,width,height,scaleX,scaleY,rotation);
        if(direction==1)
            batch.draw(Assets.getInstance().bulletTR,x,y,width,height);
        else
            batch.draw(Assets.getInstance().bulletTR,x+width,y,-width,height);
    }

    @Override
    public void update(float delta) {

        int directionY = 1;
        int directionX = 1;

        x += directionX * speedX * delta * direction;

    }
}
