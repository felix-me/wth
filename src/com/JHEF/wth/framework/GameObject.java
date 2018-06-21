package com.JHEF.wth.framework;

import com.JHEF.wth.window.Game;

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

    protected Texture tex = Game.getTexture();

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
     * returns the collision bounds of a given game object
     */
    public abstract Rectangle getBounds();
    public abstract Rectangle getBoundsTop();
    public abstract Rectangle getBoundsBottom();
    public abstract Rectangle getBoundsLeft();
    public abstract Rectangle getBoundsRight();

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

    protected void setVelX(float velX) {
        this.velX = velX;
    }

    protected void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    boolean isJumping() {
        return jumping;
    }

    void setJumping() {
        this.jumping = true;
    }

    public ObjectId getId() {
        return id;
    }

}
