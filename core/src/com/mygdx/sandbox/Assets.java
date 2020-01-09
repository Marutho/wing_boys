package com.mygdx.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    private static Assets instance = null;
    public Texture player;
    public Texture bullet;
    public Texture bg;
    public TextureRegion bgTR;
    public Texture bgMenu;
    public TextureRegion bgMenuTR;
    public TextureRegion bulletTR;
    public TextureRegion[] textureRegions;


    //HUD
    public Texture button;
    public BitmapFont font;

    //SPECIAL ENEMY
    public TextureRegion[] specialRegions;
    public Animation<TextureRegion> specialAnim;

    public TextureRegion[] specialBulletRegions;
    public Animation<TextureRegion> specialBulletAnim;

    //player things
    public TextureRegion[] idleRegions;
    public Animation<TextureRegion> idleAnim;


    public TextureRegion[] rightRegions;
    public Animation<TextureRegion> rightAnim;

    public TextureRegion[] leftRegions;
    public Animation<TextureRegion> leftAnim;

    public TextureRegion[] downRegions;
    public Animation<TextureRegion> downAnim;

    public TextureRegion[] upRegions;
    public Animation<TextureRegion> upAnim;

    public TextureRegion[] shootRegions;
    public Animation<TextureRegion> shootAnim;

    //ENEMY THINGS
    public TextureRegion[] simpleRegions;
    public Animation<TextureRegion> simpleAnim;
    public TextureRegion[] simpleRegions2;
    public Animation<TextureRegion> simpleAnim2;
    public TextureRegion[] simpleBulletRegions;
    public Animation<TextureRegion> simpleBulletAnim;
    //


    private Assets() {
        bullet=new Texture("Wing/Bullet.png");
        bulletTR = new TextureRegion(bullet);

        //HUD
        button = createButtonTexture();
        font = new BitmapFont();
        font.getData().setScale(2);
        /*effect = new ParticleEffect();  //new empty effect
        effect.load(Gdx.files.internal("Effects/EFECT.p"), Gdx.files.internal(""));
        effect.scaleEffect(0.005f);*/

        bg = new Texture ("Background.png");
        bgMenu = new Texture("BackgroundMenu.png");

        bg.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
        bgMenu.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);

        textureRegions = new TextureRegion[Constants.TEXREG_NUMBER];

        for(int i=0; i<Constants.TEXREG_NUMBER; i++)
        {
            textureRegions[i] = GOFactory.textureFromTextureAtlas("mario"+(i+1));
        }



        //TORNADO ENEMY

        specialRegions=new TextureRegion[Constants.TEXREG_NUMBER];
        specialRegions[0]= GOFactory.specialEnemyFromTextureAtlas("tornado");
        specialRegions[1]= GOFactory.specialEnemyFromTextureAtlas("Tornado2");
        specialAnim = new Animation<TextureRegion>(0.05f,specialRegions);
        specialAnim.setPlayMode(Animation.PlayMode.LOOP);

        specialBulletRegions=new TextureRegion[Constants.TORNADO_NUMBER];
        for(int i=0; i<Constants.TORNADO_NUMBER;i++)
        {
            specialBulletRegions[i] = GOFactory.specialEnemyBulletFromTextureAtlas("Torna"+i);
        }
        specialBulletAnim = new Animation<TextureRegion>(0.1f,specialBulletRegions);
        specialBulletAnim.setPlayMode(Animation.PlayMode.LOOP);

        //////////////////////////////////////////PLAYER

        //IDDLE
        idleRegions=new TextureRegion[Constants.TEXREG_NUMBER];
        idleRegions[0]= GOFactory.playerFromTextureAtlas("Down1");
        idleRegions[1]= GOFactory.playerFromTextureAtlas("Down1");
        idleAnim = new Animation<TextureRegion>(0.5f,idleRegions);
        idleAnim.setPlayMode(Animation.PlayMode.LOOP);
        //RIGHT
        rightRegions=new TextureRegion[Constants.RIGHT_NUMBER];
        rightRegions[0]= GOFactory.playerFromTextureAtlas("Right1");
        rightRegions[1]= GOFactory.playerFromTextureAtlas("Right2");
        rightRegions[2]= GOFactory.playerFromTextureAtlas("Right3");
        rightRegions[3]= GOFactory.playerFromTextureAtlas("Right4");
        rightAnim = new Animation<TextureRegion>(0.1f,rightRegions);
        rightAnim.setPlayMode(Animation.PlayMode.NORMAL);
        //LEFT
        leftRegions=new TextureRegion[Constants.RIGHT_NUMBER];
        leftRegions[0]= GOFactory.playerExtensionFromTextureAtlas("Left1");
        leftRegions[1]= GOFactory.playerExtensionFromTextureAtlas("Left2");
        leftRegions[2]= GOFactory.playerExtensionFromTextureAtlas("Left3");
        leftRegions[3]= GOFactory.playerExtensionFromTextureAtlas("Left4");
        leftAnim = new Animation<TextureRegion>(0.1f,leftRegions);
        leftAnim.setPlayMode(Animation.PlayMode.NORMAL);
        //Down
        downRegions=new TextureRegion[Constants.TEXREG_NUMBER];
        downRegions[0]= GOFactory.playerFromTextureAtlas("Down2");
        downRegions[1]= GOFactory.playerFromTextureAtlas("Down2");
        downAnim = new Animation<TextureRegion>(0.5f,downRegions);
        downAnim.setPlayMode(Animation.PlayMode.LOOP);
        //Up
        upRegions=new TextureRegion[Constants.TEXREG_NUMBER];
        upRegions[0]= GOFactory.playerFromTextureAtlas("Up1");
        upRegions[1]= GOFactory.playerFromTextureAtlas("Up2");
        upAnim = new Animation<TextureRegion>(0.5f,upRegions);
        upAnim.setPlayMode(Animation.PlayMode.NORMAL);
        //SHOOT
        shootRegions=new TextureRegion[Constants.SHOOT_NUMBER];
        shootRegions[0]= GOFactory.playerFromTextureAtlas("Attack1");
        shootRegions[1]= GOFactory.playerFromTextureAtlas("Attack2");
        shootRegions[2]= GOFactory.playerFromTextureAtlas("Attack3");
        shootRegions[3]= GOFactory.playerFromTextureAtlas("Attack4");
        shootRegions[4]= GOFactory.playerFromTextureAtlas("Attack5");
        shootAnim = new Animation<TextureRegion>(0.05f,shootRegions);
        shootAnim.setPlayMode(Animation.PlayMode.NORMAL);


        //SIMPLE ENEMY
        simpleRegions=new TextureRegion[Constants.SIMPLE_NUMBER];
        simpleRegions[0]= GOFactory.simpleEnemyFromTextureAtlas("Simple1");
        simpleRegions[1]= GOFactory.simpleEnemyFromTextureAtlas("Simple2");
        simpleRegions[2]= GOFactory.simpleEnemyFromTextureAtlas("Simple3");
        simpleAnim = new Animation<TextureRegion>(0.2f,simpleRegions);
        simpleAnim.setPlayMode(Animation.PlayMode.LOOP);

        simpleRegions2=new TextureRegion[Constants.SIMPLE_NUMBER];
        simpleRegions2[0]= GOFactory.simpleEnemy2FromTextureAtlas("Simple1");
        simpleRegions2[1]= GOFactory.simpleEnemy2FromTextureAtlas("Simple2");
        simpleRegions2[2]= GOFactory.simpleEnemy2FromTextureAtlas("Simple3");
        simpleAnim2 = new Animation<TextureRegion>(0.2f,simpleRegions2);
        simpleAnim2.setPlayMode(Animation.PlayMode.LOOP);

        //SIMPLE ENEMY BULLET
        simpleBulletRegions=new TextureRegion[Constants.SIMPLE_NUMBER];
        simpleBulletRegions[0]= GOFactory.simpleEnemyBulletFromTextureAtlas("SBullet1");
        simpleBulletRegions[1]= GOFactory.simpleEnemyBulletFromTextureAtlas("SBullet2");
        simpleBulletRegions[2]= GOFactory.simpleEnemyBulletFromTextureAtlas("SBullet3");
        simpleBulletAnim = new Animation<TextureRegion>(0.2f,simpleBulletRegions);
        simpleBulletAnim.setPlayMode(Animation.PlayMode.LOOP);

    }

    private Texture createButtonTexture() {
        Pixmap pm = new Pixmap(10,10, Pixmap.Format.RGBA8888);
        pm.setColor(0,0,0,1);
        pm.drawRectangle(0,0,10,10);
        pm.setColor(0,0,1,1);
        pm.fillRectangle(1,1,8,8);
        return new Texture(pm);
    }

    public static Assets getInstance() {
        if(instance == null) {
            instance = new Assets();
        }
        return instance;
    }

}