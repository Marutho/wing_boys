package com.mygdx.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;


public class ArcadeHandler implements ControllerListener {

    public Level lvl;

    public  ArcadeHandler(Level level)
    {
        lvl=level;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        //CONTROLLER 1
        if(Controllers.getControllers().indexOf(controller,true) == 0) {
            if(buttonCode == 0)
                lvl.player.Shoot();
                lvl.player.setSpecialSActivated(true);

                if(buttonCode == 6)

                    if(buttonCode == 9)
                        Gdx.app.exit();

            //man.switchColor(buttonCode);
            return true;
        }

        //CONTROLLER 2
        if(lvl.numPlayers==2)
        {
            if(Controllers.getControllers().indexOf(controller,true) == 1) {
                if(buttonCode == 0)
                    lvl.player2.Shoot();
                lvl.player2.setSpecialSActivated(true);

                if(buttonCode == 6)


                    if(buttonCode == 9)
                        Gdx.app.exit();

                //man.switchColor(buttonCode);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        //CONTROLLER 1
        if(Controllers.getControllers().indexOf(controller,true) == 0) {
            if(buttonCode == 0)
                lvl.player.setSpecialSActivated(false);

            if(buttonCode == 1)




            //man.switchColor(buttonCode);
            return true;
        }

        //CONTROLLER 2
        if(lvl.numPlayers==2)
        {
            if(Controllers.getControllers().indexOf(controller,true) == 1) {
                if(buttonCode == 0)
                    lvl.player2.setSpecialSActivated(false);

                if(buttonCode == 1)





                    //man.switchColor(buttonCode);
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {


        //Controller 1
        if(Controllers.getControllers().indexOf(controller,true) == 0){

            //y axis
            if(axisCode == 0){

                //up
                if(value == -1){
                    lvl.player.directionY=1;
                    return true;
                }
                //down
                else if(value == 1){
                    lvl.player.directionY=-1;
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){
                    lvl.player.directionY=0;
                    return true;
                }

            }
            //x axis
            else if(axisCode == 1){

                //left
                if(value == -1){
                    lvl.player.setDirectionX(-1);
                    return true;
                }
                //rigth
                else if(value == 1){
                    lvl.player.setDirectionX(1);
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){
                    lvl.player.setDirectionX(0);
                    return true;
                }

            }

        }
        //Controller 2
        if(lvl.numPlayers==2)
        {
            if(Controllers.getControllers().indexOf(controller,true) == 1){

                //y axis
                if(axisCode == 0){

                    //up
                    if(value == -1){
                        lvl.player2.directionY=1;
                        return true;
                    }
                    //down
                    else if(value == 1){
                        lvl.player2.directionY=-1;
                        return true;
                    }
                    //center
                    else if(value <0.1 && value>-0.1){
                        lvl.player2.directionY=0;
                        return true;
                    }

                }
                //x axis
                else if(axisCode == 1){

                    //left
                    if(value == -1){
                        lvl.player2.setDirectionX(-1);
                        return true;
                    }
                    //rigth
                    else if(value == 1){
                        lvl.player2.setDirectionX(1);
                        return true;
                    }
                    //center
                    else if(value <0.1 && value>-0.1){
                        lvl.player2.setDirectionX(0);
                        return true;
                    }

                }

            }
        }

        return false;
    }

    @Override
    public void connected(Controller controller) {
        //Do nothing
    }

    @Override
    public void disconnected(Controller controller) {
        //Do nothing
    }
    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}

