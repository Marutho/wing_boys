package com.mygdx.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player extends GameObject {

    ParticleEffect effect;


    public float timeToSpawn;
    int directionX = 0;
    int directionY = 0;
    boolean shooting = false;
    float shootingTimer=0;
    private float statime;
    private boolean specialSActivated = false;
    float specialShoot=0.0f;
    Sound s = Gdx.audio.newSound(Gdx.files.internal("SonidosMoviles/Shoot.wav"));
    Rectangle nextPosition;
    int numPlayer;
    boolean canMove=true;

    public Player(Level level, float posX, float posY, int numPlayer){

        super(level);

        effect = new ParticleEffect();  //new empty effect
        effect.load(Gdx.files.internal("Effects/efecto.p"), Gdx.files.internal(""));

        this.numPlayer=numPlayer;
        x = posX;
        y = posY;

        lives=300;
        lvl=level;
        timeToSpawn=3;

        rotation = 0;

        effect.scaleEffect(0.03f);
        effect.flipY();


        //width = Assets.getInstance().textureRegions[0].getRegionWidth();
        //height = Assets.getInstance(.textureRegions[0].getRegionHeight();

        width = 1.0f;
        height = 1.0f;

        //scaleX = 1.0f;
       // scaleY = 1.0f;

        speedX = 2.0f;
        speedY = 2.0f;

        nextPosition = new Rectangle();
    }

    public void setDirectionX(int newDirection){
        directionX = newDirection;
        statime=0.0f;
    }
    public void setDirectionY(int newDirection){
        directionY = newDirection;
        statime=0.0f;
    }

    public void setSpecialSActivated(boolean state){
        specialSActivated=state;
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {
        effect.draw(batch);
        if(directionX==0 && directionY==0 && shooting==false)
        {
            TextureRegion txt = Assets.getInstance().idleAnim.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);

        }
        else if(directionX==1 )
        {

            TextureRegion txt = Assets.getInstance().rightAnim.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);
        }
        else if(directionX==0 && directionY==-1)
        {

            TextureRegion txt = Assets.getInstance().downAnim.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);
        }
        else if(directionX==0 && directionY==1 )
        {
            TextureRegion txt = Assets.getInstance().upAnim.getKeyFrame(staTime,false);
            batch.draw(txt,x,y,width,height);
        }
        else if(directionX==-1)
        {
            TextureRegion txt = Assets.getInstance().leftAnim.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);
        }
        else if(directionX==0 && directionY==0 && shooting==true )
        {
            TextureRegion txt = Assets.getInstance().shootAnim.getKeyFrame(staTime, true);
            batch.draw(txt,x,y,width,height);
        }


    }

    @Override
    public void update(float delta) {


        Move(delta);
        timeToSpawn+=delta;

        effect.update(delta);

        if(specialSActivated)
        {
            specialShoot+=delta;
            specialShoot();
        }

        if(shooting==true)
        {
            shootingTimer+=delta;
            if(shootingTimer> Assets.getInstance().shootAnim.getAnimationDuration())
            {
                shooting=false;
                shootingTimer=0.0f;
            }
        }

        effect.start();
        effect.setPosition(x+width/2,y+height/2);
        effect.allowCompletion();
    }

    public void Shoot()
    {
        SoundManager.reproduceSounds(1);
        if(directionX==1 || directionX ==0)
        {
            shooting=true;
            lvl.bulletsPlayer.add(new playerBullet(x+width/2,y-height/4,1,0));


        }
        else
        {
            shooting=true;
            lvl.bulletsPlayer.add(new playerBullet(x-width,y-height/4,-1,180));
        }


    }

    public void specialShoot()
    {

        if(specialShoot>3.0f) {
            if(directionX==1 || directionX ==0)
            {
                shooting=true;
                lvl.bulletsPlayer.add(new playerBullet(x - width/4 , y-height/2 , 2f, 2f,3,3,1,0));
            }
            else
            {
                shooting=true;
                lvl.bulletsPlayer.add(new playerBullet(x - width/2 , y-height/2 , 2f, 2f,3,3,-1,180));
            }
            specialShoot=0.0f;
        }

    }

    public void Move(float delta){

        nextPosition.set(x+directionX * speedX * delta, y + directionY * speedY * delta, width, height );
            if(collisionHelper.checkIfInside(nextPosition,lvl.bg.getBounds()) && canMove)
            {
                y += directionY * speedY * delta;
                x += directionX * speedX * delta;
            }
        }



    }

