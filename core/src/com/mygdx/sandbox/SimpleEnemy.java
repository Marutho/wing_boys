package com.mygdx.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SimpleEnemy extends GameObject {

    float timeToFire=0.0f;
    float directionY;
    float directionX;

    public SimpleEnemy(Level level, float posX, float posY){

        super(level);
        x = posX;
        y = posY;

        rotation = 0;

        //width = Assets.getInstance().textureRegions[0].getRegionWidth();
        //height = Assets.getInstance(.textureRegions[0].getRegionHeight();

        width = 1.0f;
        height = 1.0f;

        //scaleX = 1.0f;
        //scaleY = 1.0f;

        speedX = 1.0f;
        speedY = 1.0f;

        float xPlayer=lvl.player.x;
        float yPlayer=lvl.player.y;

        float magnitude=(float)Math.sqrt((xPlayer-x)*(xPlayer-x)+(yPlayer-y)*(yPlayer-y));

        directionX=(xPlayer-x)/magnitude;
        directionY=(yPlayer-y)/magnitude;
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {

        if(directionX==-1)
        {
            TextureRegion txt = Assets.getInstance().simpleAnim.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);
        }else if(directionX==1)
        {
            TextureRegion txt = Assets.getInstance().simpleAnim2.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);
        }
        else{
            TextureRegion txt = Assets.getInstance().simpleAnim.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);
        }

    }

    @Override
    public void update(float delta) {


        timeToFire+=delta;
        if(timeToFire>=4)
        {
            SoundManager.reproduceSounds(2);
            lvl.bulletsEnemy.add(new EnemyBullet(lvl,x,y));
            timeToFire=0;
        }

        x += directionX * speedX * delta;
        y += directionY * speedY * delta;


    }
}
