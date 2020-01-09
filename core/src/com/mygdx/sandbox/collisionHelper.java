package com.mygdx.sandbox;

import com.badlogic.gdx.math.Rectangle;

public class collisionHelper {

    public static boolean checkCollision(GameObject GO1, GameObject GO2)
    {
        if(GO1.getBounds().overlaps(GO2.getBounds()))
        {
            return true;
        }
        return false;
    }


    public static boolean checkIfInside(Rectangle GO1, Rectangle GO2)
    {
        if((GO1.x+GO1.width)<GO2.x+GO2.width &&
        GO1.y+GO1.height<GO2.y+GO2.height &&
        GO1.x>GO2.x && GO1.y>GO2.y )
        {
            return true;
        }
        return false;


    }
}
