package com.mygdx.sandbox;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyBullet extends GameObject {

    float directionY;
    float directionX;

    public EnemyBullet(Level level, float posX, float posY){
        super(level);
        x = posX;
        y = posY;


        rotation = 0;

        //width = Assets.getInstance().textureRegions[0].getRegionWidth();
        //height = Assets.getInstance(.textureRegions[0].getRegionHeight();

        width = 0.5f;
        height = 0.5f;

        //scaleX = 1.0f;
       // scaleY = 1.0f;

        speedX = 3.0f;
        speedY = 3.0f;

        float xPlayer=lvl.player.x;
        float yPlayer=lvl.player.y;

        float magnitude=(float)Math.sqrt((xPlayer-x)*(xPlayer-x)+(yPlayer-y)*(yPlayer-y));

        directionX=(xPlayer-x)/magnitude;
        directionY=(yPlayer-y)/magnitude;
    }
    @Override
    public void draw(SpriteBatch batch, float staTime) {
        TextureRegion txt = Assets.getInstance().simpleBulletAnim.getKeyFrame(staTime, true);
        batch.draw(txt,x,y,width,height);
    }

    @Override
    public void update(float delta) {

        x += directionX * speedX * delta;
        y += directionY * speedY * delta;

    }
}
