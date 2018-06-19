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

    public abstract float getX();
    public abstract float getY();
    public abstract void setX(float x);
    public abstract void setY(float y);

    public abstract float getVelX();
    public abstract float getVelY();
    public abstract void setVelX(float velX);
    public abstract void setVelY(float velY);

    public abstract ObjectId getId();

}
