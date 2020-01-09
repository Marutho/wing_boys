package com.mygdx.sandbox;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.sandbox.HUD.HUD;
import com.mygdx.sandbox.HUD.TextButton;

public class Level {


    public int numPlayers;
    public ArrayList<GameObject> objects;
    public ArrayList<GameObject> bulletsPlayer;
    public ArrayList<GameObject> bulletsEnemy;
    public ArrayList<GameObject> objectsToRemove;
    public Player player;
    public Player player2;
    public float timeToSpawn=0.0f;
    public Background bg;
    public CameraHelper helper;
    public int lives;


    //HUD THINGS
    public OrthographicCamera hudCamera;
    HUD hud;

    public Level(int numPlayers, CameraHelper helper, int lives)
    {
        this.lives=lives;
        Controllers.clearListeners();
        SoundManager.reproduceMusic();
        this.numPlayers=numPlayers;
        this.helper=helper;
        objects= new ArrayList<GameObject>();
        bulletsPlayer = new ArrayList<GameObject>();
        bulletsEnemy = new ArrayList<GameObject>();
        objectsToRemove = new ArrayList<GameObject>();


        //HUD THINGS
        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        hud = new HUD();

        if(Gdx.app.getType() == Application.ApplicationType.Android){
            TextButton b1 = new TextButton("SHOOT",10,-200,580,200);

            hud.add(b1);
        }


        //
        player = new Player(this,2,6,1);
        player.lives=lives;
        if(numPlayers==2)
        {
            player2 = new Player(this,5,7,2);
            player2.lives=lives;
        }



        bg = GOFactory.generateBR(this,-5,-5,100,20);
        GameObject specialEnemy = GOFactory.generateSpecialEnemy(this,5,5);
        GameObject simpleEnemy = GOFactory.generateSimpleEnemy(this,6,6);
        objects.add(specialEnemy);
        objects.add(simpleEnemy);
        //  objects.add(player);

    }

    public void update(float delta){

        timeToSpawn+=delta;

        for(int i=0; i<objects.size(); i++)
        {
            objects.get(i).update(delta);
        }
        for(int i=0; i<bulletsPlayer.size(); i++)
        {
            bulletsPlayer.get(i).update(delta);
        }
        for(int i=0; i<bulletsEnemy.size(); i++)
        {
            bulletsEnemy.get(i).update(delta);
        }
        //System.out.println(timeToSpawn);
        if(timeToSpawn>=Constants.TIME_TO_SPAWN)
        {
            //System.out.println("eeeeeeeeee spawn");
            GameObject simpEnemy = GOFactory.generateSimpleEnemy(this,(int) (Math.random() * (player.x+player.width/2+helper.camera.viewportWidth/2-(player.x-player.width/2-helper.camera.viewportWidth/2)+10) + (player.x-player.width/2-helper.camera.viewportWidth/2)),(int) (Math.random() * bg.height) + 1);
            objects.add(simpEnemy);
            GameObject specialEnemy = GOFactory.generateSpecialEnemy(this,(int) (Math.random() * (player.x+player.width/2+helper.camera.viewportWidth/2-(player.x-player.width/2-helper.camera.viewportWidth/2)+10) + (player.x-player.width/2-helper.camera.viewportWidth/2)),(int) (Math.random() * bg.height) + 1);
            objects.add(specialEnemy);
            timeToSpawn=0;
        }

        player.update(delta);

        if(numPlayers==2)
        {
            player2.update(delta);
        }


        collisionCenter(delta);

        bg.update(delta);
    }

    public void render(SpriteBatch batch, float staTime) {


        batch.begin();
        batch.setColor(1,1,1,1);
        bg.draw(batch, staTime);
        for(int i=0; i<objects.size(); i++)
        {
            objects.get(i).draw(batch, staTime);
        }
        for(int i=0; i<bulletsPlayer.size(); i++)
        {
            bulletsPlayer.get(i).draw(batch, staTime);
        }
        for(int i=0; i<bulletsEnemy.size(); i++)
        {
            bulletsEnemy.get(i).draw(batch, staTime);
        }
        player.draw(batch, staTime);

        batch.end();

        if(numPlayers==2)
        {
            batch.begin();
            batch.setColor(0,0.5f,1,1);
            player2.draw(batch, staTime);
            batch.end();
        }



        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        hud.render(batch);
        batch.end();


    }

    public void collisionCenter(float delta){


        // ENEMIGO PETA AL TOCAR BALA player
        for(int i=0; i<objects.size(); i++)
        {
            for(int j=0; j<bulletsPlayer.size(); j++)
            {
                if(collisionHelper.checkCollision(objects.get(i),bulletsPlayer.get(j)))
                {
                    //se ha quedao sin vidas?
                    objects.get(i).lives-=bulletsPlayer.get(j).dmg;
                    if(objects.get(i).lives<=0)
                    {
                        SoundManager.reproduceSounds(5);
                        objectsToRemove.add(objects.get(i));
                    }

                    bulletsPlayer.get(j).lives-=1;
                    if(bulletsPlayer.get(j).lives<=0)
                    {
                        SoundManager.reproduceSounds(5);
                        objectsToRemove.add(bulletsPlayer.get(j));
                    }

                }
            }
        }

        //SI LA BALA TOCA AL JUGADOR QUITA VIDA HASTA QUE MATA
        for(int i=0; i<bulletsEnemy.size(); i++)
        {
            if(collisionHelper.checkCollision(player,bulletsEnemy.get(i))) {
                //se ha quedao sin vidas?
                player.lives-=bulletsEnemy.get(i).dmg;
                SoundManager.reproduceSounds(4);
                if(player.lives<=0)
                {
                    System.out.println("Has muerto");
                    System.exit(0);
                }
                objectsToRemove.add(bulletsEnemy.get(i));
            }else if(numPlayers==2) {
                if (collisionHelper.checkCollision(player2, bulletsEnemy.get(i))) {
                    //se ha quedao sin vidas?
                    player2.lives -= bulletsEnemy.get(i).dmg;
                    SoundManager.reproduceSounds(4);
                    if (player2.lives <= 0) {
                        System.out.println("Has muerto");
                        System.exit(0);
                    }
                    objectsToRemove.add(bulletsEnemy.get(i));
                }
            }
        }


        //SI EL ENEMY SIMPLE SALE REVIENTA
        for(int i=0; i<objects.size(); i++)
        {
            if(!collisionHelper.checkCollision(objects.get(i),bg)) {
                //se ha quedao sin vidas?
                SoundManager.reproduceSounds(5);
                objectsToRemove.add(objects.get(i));
            }
        }

        //SI LA BALA SALE REVIENTA (ENEMIGA)
        for(int i=0; i<bulletsEnemy.size(); i++)
        {
            if(!collisionHelper.checkCollision(bulletsEnemy.get(i),bg)) {
                //se ha quedao sin vidas?
                objectsToRemove.add(bulletsEnemy.get(i));
            }
        }

        //SI LA BALA SALE REVIENTA (PLAYER)
        for(int i=0; i<bulletsPlayer.size(); i++)
        {
            if(!collisionHelper.checkCollision(bulletsPlayer.get(i),bg)) {
                //se ha quedao sin vidas?
                objectsToRemove.add(bulletsPlayer.get(i));
            }
        }

        //ENEMIGO TOCA PLAYER
        for(int i=0; i<objects.size(); i++)
        {
            if(collisionHelper.checkCollision(player,objects.get(i))) {
                //se ha quedao sin vidas?
                player.lives--;
                SoundManager.reproduceSounds(4);
                if(player.lives<=0)
                {
                    System.out.println("Has muerto");
                    System.exit(0);
                }
                SoundManager.reproduceSounds(5);
                objectsToRemove.add(objects.get(i));
            }else if(numPlayers==2) {
                if (collisionHelper.checkCollision(player2, objects.get(i))) {
                    //se ha quedao sin vidas?
                    player2.lives--;
                    SoundManager.reproduceSounds(4);
                    if (player2.lives <= 0) {
                        System.out.println("Has muerto");
                        System.exit(0);
                    }
                    SoundManager.reproduceSounds(5);
                    objectsToRemove.add(objects.get(i));
                }
            }
        }

        //BALA CONTRA BALA PUM PAM
        for(int i=0; i<bulletsEnemy.size(); i++)
        {
            for(int j=0; j<bulletsPlayer.size(); j++)
            {
                if(collisionHelper.checkCollision(bulletsEnemy.get(i),bulletsPlayer.get(j)))
                {
                    //se ha quedao sin vidas?
                    bulletsEnemy.get(i).lives-=1;
                    if(bulletsEnemy.get(i).lives<=0)
                    {
                        SoundManager.reproduceSounds(6);
                        objectsToRemove.add(bulletsEnemy.get(i));
                    }

                    bulletsPlayer.get(j).lives-=1;
                    if(bulletsPlayer.get(j).lives<=0){
                        objectsToRemove.add(bulletsPlayer.get(j));
                    }
                }
            }
        }


        if(numPlayers==2)
        {
            //PLAYER 2 CHOCA VIEWPORT DEL JUEGADOR
            if(collisionHelper.checkIfInside(player2.nextPosition,helper.getBounds(player)))
            {
                player2.canMove=true;
            }else if(!collisionHelper.checkIfInside(player2.nextPosition,helper.getBounds(player))){
                if(player.directionX==0 && player.directionY==0 )
                {
                    player2.canMove=false;
                    player2.directionX=0;
                    player2.directionY=0;
                }else if(player.directionX==1 && player.directionY==0)
                {
                    player2.canMove=true;
                    player2.directionX=1;
                    player2.directionY=0;
                }else if(player.directionX==-1 && player.directionY==0)
                {
                    player2.canMove=true;
                    player2.directionX=-1;
                    player2.directionY=0;
                }else if(player.directionX==0 && player.directionY==1)
                {
                    player2.canMove=true;
                    player2.directionY=1;
                    player2.directionX=0;
                }else if(player.directionX==0 && player.directionY==-1)
                {
                    player2.canMove=true;
                    player2.directionY=-1;
                    player2.directionX=0;
                }
                else if(player.directionX==1 && player.directionY==-1)
                {
                    player2.canMove=true;
                    player2.directionY=-1;
                    player2.directionX=1;
                }else if(player.directionX==-1 && player.directionY==1)
                {
                    player2.canMove=true;
                    player2.directionY=1;
                    player2.directionX=-1;
                }
                else if(player.directionX==-1 && player.directionY==-1)
                {
                    player2.canMove=true;
                    player2.directionY=-1;
                    player2.directionX=-1;
                }else if(player.directionX==1 && player.directionY==1)
                {
                    player2.canMove=true;
                    player2.directionY=1;
                    player2.directionX=1;
                }

            }
        }



        //
        bulletsPlayer.removeAll(objectsToRemove);
        objects.removeAll(objectsToRemove);
        bulletsEnemy.removeAll(objectsToRemove);
        objectsToRemove.clear();
    }

    public void drawDebug(ShapeRenderer shapeRender) {

        for(int i=0; i<objects.size(); i++)
        {
            objects.get(i).drawDebug(shapeRender);
        }
        for(int i=0; i<bulletsPlayer.size(); i++)
        {
            bulletsPlayer.get(i).drawDebug(shapeRender);
        }
        for(int i=0; i<bulletsEnemy.size(); i++)
        {
            bulletsEnemy.get(i).drawDebug(shapeRender);
        }
        player.drawDebug(shapeRender);
        if(numPlayers==2)
        {
            player2.drawDebug(shapeRender);
        }
        helper.drawDebug(shapeRender,player);

    }

    Vector3 pointHUD;
    Vector3 pointGame;


}