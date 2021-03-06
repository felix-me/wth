package com.JHEF.wth.window;

import com.JHEF.wth.framework.GameObject;

public class Camera{

    private float x, y;

    Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void tick(GameObject player) {
        x = -player.getX() + Game.WIDTH/2;
    }
}
