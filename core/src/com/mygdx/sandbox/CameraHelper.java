package com.mygdx.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class CameraHelper {

    public OrthographicCamera camera;
    public String TAG_CAMERA = "CAMERA";
    public Rectangle rectangle;
    public boolean canMove=true;

    public CameraHelper(){

        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        rectangle=new Rectangle();
    }

    public void zoomIn(float delta)
    {
        camera.zoom -= 1*delta;
        camera.update();
       // Gdx.app.debug(TAG_CAMERA, "Camera has a zoom value of: " + camera.zoom);
    }

    public void zoomOut(float delta)
    {
        camera.zoom += 1*delta;
        camera.update();
        //Gdx.app.debug(TAG_CAMERA, "Camera has a zoom value of: " + camera.zoom);
    }

    public void moveCamera(float delta)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.K)){zoomIn(delta);}
        if(Gdx.input.isKeyPressed(Input.Keys.L)){zoomOut(delta);}
        camera.update();
    }

    public void followGO(GameObject go, GameObject bg)
    {
            if (collisionHelper.checkIfInside(xCamera(go), bg.getBounds())) {
                camera.position.x = go.x + go.width / 2;
            }

            if (collisionHelper.checkIfInside(yCamera(go), bg.getBounds())) {
                camera.position.y = go.y + go.height / 2;
            }


            camera.update();

    }

    //DEVUELVE Y
    public Rectangle yCamera(GameObject go)
    {
        GameObject checkBoundsY= new GameObject(camera.position.x-camera.viewportWidth/2, go.y+go.height/2-camera.viewportHeight/2, camera.viewportWidth, camera.viewportHeight) {
            @Override
            public void draw(SpriteBatch batch, float staTime) {

            }

            @Override
            public void update(float delta) {

            }
        };
        Rectangle rectY = checkBoundsY.getBounds();
        return rectY;
    }

    //DEVUELVE X
    public Rectangle xCamera(GameObject go){
        GameObject checkBoundsX= new GameObject(go.x+go.width/2-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, camera.viewportWidth, camera.viewportHeight) {
            @Override
            public void draw(SpriteBatch batch, float staTime) {

            }

            @Override
            public void update(float delta) {

            }
        };

        Rectangle rectX = checkBoundsX.getBounds();
        return rectX;
    }

    //Rectangle
    public Rectangle getBounds(GameObject go){
        rectangle.set(go.x+go.width/2-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, camera.viewportWidth, camera.viewportHeight);
        return rectangle;
    }


    public void resize(float width, float height)
    {
        camera.viewportWidth=Constants.VIEWPORT_HEIGHT/height * width;
        camera.update();
    }

    public void drawDebug(ShapeRenderer shape, GameObject go)
    {
        shape.setColor(Color.RED);
        shape.rect(go.x+go.width/2-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, camera.viewportWidth, camera.viewportHeight);
    }

}
