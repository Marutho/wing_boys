package com.mygdx.sandbox;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GOFactory {


    public static SimpleEnemy generateSimpleEnemy(Level level, float posX, float posY){

        SimpleEnemy se = new SimpleEnemy(level, posX, posY);
        return se;
    }

    public static SpecialEnemy generateSpecialEnemy(Level level, float posX, float posY){

        SpecialEnemy sp = new SpecialEnemy(level, posX, posY);
        return sp;
    }

    public static TextureRegion textureFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("mario/pack1.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion setasFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("setas/setas.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion playerFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("Wing/WingBoi.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion playerExtensionFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("Wing/Wing2.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion simpleEnemyFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("SimpleEnemy/SimpleEnemy.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion simpleEnemy2FromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("SimpleEnemy2/SimpleEnemy2.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion simpleEnemyBulletFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("SimpleBullet/SimpleBullet.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion specialEnemyBulletFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("Tornado/Tornado.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static TextureRegion specialEnemyFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("TornaEnemy/TornaEnemy.atlas");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }

    public static Background generateBR(Level level, float x, float y, float width, float height)
    {
        float bgFactorWidth = Assets.getInstance().bg.getWidth() * 1/100f;
        float bgFactorHeight = Assets.getInstance().bg.getHeight() * 1/20f;
        Assets.getInstance().bgTR = new TextureRegion(Assets.getInstance().bg,(int)(bgFactorWidth*width),(int)(bgFactorHeight* height));
        Background generatedBg = new Background(level,x,y,width,height);

        return generatedBg;
    }

    public static BackgroundMenu generateBRMenu(float x, float y, float width, float height)
    {
        float bgFactorWidth = Assets.getInstance().bgMenu.getWidth() * 1/100f;
        float bgFactorHeight = Assets.getInstance().bgMenu.getHeight() * 1/280f;
        Assets.getInstance().bgMenuTR = new TextureRegion(Assets.getInstance().bgMenu,(int)(bgFactorWidth*width),(int)(bgFactorHeight* height));
        BackgroundMenu generatedBgMenu = new BackgroundMenu(x,y,width,height);

        return generatedBgMenu;
    }
}
