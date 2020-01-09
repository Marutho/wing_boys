package com.mygdx.sandbox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class SpecialEnemy extends GameObject {

    float timeToFire=0.0f;
    float directionY;
    float directionX;
    int indiceWaypoint =0;
    Vector2 currentWayPoint;
    ArrayList<Vector2> enemyPositions;
    float timeToChangeWaypoint;
    float auxX;
    float auxY;

    public SpecialEnemy(Level level, float posX, float posY){

        super(level);

        enemyPositions=new ArrayList<Vector2>();

        enemyPositions.add(new Vector2(5,0));
        enemyPositions.add(new Vector2(0,-5));
        enemyPositions.add(new Vector2(-5,0));
        enemyPositions.add(new Vector2(0,+5));

        auxX=0.0f;
        auxY=0.0f;

        x = posX;
        y = posY;

        indiceWaypoint=1;
        createWayPoints();
        rotation = 0;


        width = 1.0f;
        height = 1.0f;

        //scaleX = 1.0f;
       // scaleY = 1.0f;

        speedX = 1.0f;
        speedY = 1.0f;

        timeToChangeWaypoint=0.0f;
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {
        TextureRegion txt = Assets.getInstance().specialAnim.getKeyFrame(staTime, true);
        batch.draw(txt,x,y,width,height);
    }

    @Override
    public void update(float delta) {

        fire(delta);
        move(delta);
    }

    public void fire(float delta){
        timeToFire+=delta;
        if(timeToFire>= Constants.TIME_TO_FIRE)
        {
            SoundManager.reproduceSounds(3);
            lvl.bulletsEnemy.add(new SpecialBullet(lvl,x,y));
            timeToFire=0;
        }
    }

    public void move(float delta)
    {
        timeToChangeWaypoint+=delta;


        x += directionX * speedX * delta *2;
        y += directionY * speedY * delta * 2;


        //System.out.println(x+", " + y);
        if(timeToChangeWaypoint>2)
        {
            createWayPoints();
            timeToChangeWaypoint=0.0f;
        }
    }

    public void createWayPoints()
    {
        System.out.println("Estoy en: "+x+" , " + y + "Estaré en:"+ enemyPositions.get(indiceWaypoint).x +", " + enemyPositions.get(indiceWaypoint).y);
        auxX=enemyPositions.get(indiceWaypoint).x;
        auxY=enemyPositions.get(indiceWaypoint).y;
        currentWayPoint=new Vector2(x+enemyPositions.get(indiceWaypoint).x, y+enemyPositions.get(indiceWaypoint).y );
        indiceWaypoint=(indiceWaypoint+1)%enemyPositions.size();

        float magnitude=(float)Math.sqrt((currentWayPoint.x-x)*(currentWayPoint.x-x)+(currentWayPoint.y-y)*(currentWayPoint.y-y));
        System.out.println(magnitude);

        directionX=(currentWayPoint.x-x)/magnitude;
        directionY=(currentWayPoint.y-y)/magnitude;

    }
}
