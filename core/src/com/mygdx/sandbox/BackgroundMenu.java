package com.mygdx.sandbox;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundMenu extends GameObject{

    public BackgroundMenu(float posX, float posY, float width, float height){
        super();
        x=posX;
        y=posY;
        this.width=width;
        this.height=height;
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {
        //batch.draw(Assets.getInstance().bgTR,x,y,0,0,width,height,rotation);
        batch.draw(Assets.getInstance().bgMenuTR,x,y,width*10,height*10);
    }

    @Override
    public void update(float delta) {
        float speedX = 100;

        float speedY = -300;

        int amountX = (int)(speedX*delta);

        int amountY = (int)(speedY*delta);

        Assets.getInstance().bgMenuTR.setRegionX(Assets.getInstance().bgMenuTR.getRegionX()+amountX);

        //after modifying the X coordinate, the width of the TextureRegion has shrink (it has the same value as before the change) so we need to manually update it.

        Assets.getInstance().bgMenuTR.setRegionWidth(Assets.getInstance().bgMenuTR.getRegionWidth()+amountX);

       // Assets.getInstance().bgTR.setRegionY(Assets.getInstance().bgTR.getRegionY()+amountY);

        //Assets.getInstance().bgTR.setRegionHeight(Assets.getInstance().bgTR.getRegionHeight()+amountY);

    }
}
