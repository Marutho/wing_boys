package com.mygdx.sandbox;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class InputHelper implements InputProcessor {

   // public Level lvl;
    public GameScreen lvl;
    public  InputHelper(GameScreen game)
    {
        lvl=game;
    }
    public void Update(){

        if(Gdx.input.getAccelerometerX() > 0)
        {
            lvl.level.player.setDirectionX(-1);
        }
        if(Gdx.input.getAccelerometerX() < 0)
        {
            lvl.level.player.setDirectionX(1);
        }
        if(Gdx.input.getAccelerometerY() > 0)
        {
            lvl.level.player.setDirectionY(-1);
        }
        if(Gdx.input.getAccelerometerY() < 0)
        {
            lvl.level.player.setDirectionY(1);
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.A)
        {
            lvl.level.player.setDirectionX(-1);
        }
        if(keycode == Input.Keys.D)
        {
            lvl.level.player.setDirectionX(1);
        }
        if(keycode == Input.Keys.W)
        {
            lvl.level.player.setDirectionY(1);
        }
        if(keycode == Input.Keys.S)
        {
            lvl.level.player.setDirectionY(-1);
        }
        if (keycode == Input.Keys.SPACE)
        {
            lvl.level.player.Shoot();
            lvl.level.player.setSpecialSActivated(true);
        }
        //PLAYER2
        if(lvl.numPlayers==2)
        {
            if(keycode == Input.Keys.LEFT)
            {
                lvl.level.player2.setDirectionX(-1);
            }
            if(keycode == Input.Keys.RIGHT)
            {
                lvl.level.player2.setDirectionX(1);
            }
            if(keycode == Input.Keys.UP)
            {
                lvl.level.player2.setDirectionY(1);
            }
            if(keycode == Input.Keys.DOWN)
            {
                lvl.level.player2.setDirectionY(-1);
            }
            if (keycode == Input.Keys.ENTER)
            {
                lvl.level.player2.Shoot();
                lvl.level.player2.setSpecialSActivated(true);
            }
            if(keycode == Input.Keys.ESCAPE)
            {

            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.A)
        {
            lvl.level.player.setDirectionX(0);
        }
        if(keycode == Input.Keys.D)
        {
            lvl.level.player.setDirectionX(0);
        }
        if(keycode == Input.Keys.W)
        {
            lvl.level.player.setDirectionY(0);
        }
        if(keycode == Input.Keys.S)
        {
            lvl.level.player.setDirectionY(0);
        }

        if (keycode == Input.Keys.SPACE)
        {
            lvl.level.player.setSpecialSActivated(false);
        }
        //PLAYER 2
        if(lvl.numPlayers==2)
        {
            if(keycode == Input.Keys.LEFT)
            {
                lvl.level.player2.setDirectionX(0);
            }
            if(keycode == Input.Keys.RIGHT)
            {
                lvl.level.player2.setDirectionX(0);
            }
            if(keycode == Input.Keys.UP)
            {
                lvl.level.player2.setDirectionY(0);
            }
            if(keycode == Input.Keys.DOWN)
            {
                lvl.level.player2.setDirectionY(0);
            }
            if (keycode == Input.Keys.ENTER)
            {
                lvl.level.player2.setSpecialSActivated(false);
            }
        }



        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

   /* @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


    }*/

    Vector3 pointHUD;
    Vector3 pointMenu;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        pointHUD = new Vector3(screenX,screenY,0);
        pointMenu = new Vector3(screenX,screenY,0);
        //check if the click is for the HUD
        lvl.level.hudCamera.unproject(pointHUD);
        if(!lvl.level.hud.click(pointHUD.x,pointHUD.y)){


            System.out.println("Aqui deberian saltar moendos");
        }
        else
        {
            lvl.level.player.Shoot();
            lvl.level.player.setSpecialSActivated(true);
            return false;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lvl.level.player.setSpecialSActivated(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
