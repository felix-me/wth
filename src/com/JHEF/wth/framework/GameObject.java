package com.JHEF.wth.framework;

import java.awt.*;
import java.util.LinkedList;

/**
 * Abstract class to provide framework for all game objects
 */
public abstract class GameObject
{
    protected float x, y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;

    /**
     * constructor for {@link GameObject}
     * @param x pos x
     * @param y pos y
     * @param id objectId
     */
    public GameObject(float x, float y, ObjectId id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /**
     * Tick rate
     * @param object object defined
     */
    public abstract void tick(LinkedList<GameObject> object);

    /**
     * Render object
     * @param g graphic to render
     */
    public abstract void render(Graphics g);

    /**
     * getBoundsBottom
     * returns the collision bound of a player
     */
    public abstract Rectangle getBounds();

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }
    public float getVelY() {
        return velY;
    }
    public void setVelX(float velX) {
        this.velX = velX;
    }
    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public ObjectId getId() {
        return id;
    }

}
