package com.mygdx.sandbox;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background extends GameObject{

    public Background(Level level, float posX, float posY, float width, float height){
        super(level);
        x=posX;
        y=posY;
        this.width=width;
        this.height=height;


    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {
        //batch.draw(Assets.getInstance().bgTR,x,y,0,0,width,height,rotation);
        batch.draw(Assets.getInstance().bgTR,x,y,width,height);
    }

    @Override
    public void update(float delta) {
        float speedX = 100;

        float speedY = -300;

        int amountX = (int)(speedX*delta);

        int amountY = (int)(speedY*delta);

        Assets.getInstance().bgTR.setRegionX(Assets.getInstance().bgTR.getRegionX()+amountX);

        //after modifying the X coordinate, the width of the TextureRegion has shrink (it has the same value as before the change) so we need to manually update it.

        Assets.getInstance().bgTR.setRegionWidth(Assets.getInstance().bgTR.getRegionWidth()+amountX);

       // Assets.getInstance().bgTR.setRegionY(Assets.getInstance().bgTR.getRegionY()+amountY);

        //Assets.getInstance().bgTR.setRegionHeight(Assets.getInstance().bgTR.getRegionHeight()+amountY);

    }
}
