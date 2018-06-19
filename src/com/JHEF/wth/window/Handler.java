package com.JHEF.wth.window;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.objects.Block;

import java.awt.*;
import java.util.LinkedList;

/**
 * Handles game objects and stores them
 */
public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tempObject;

    /**
     * Per tick get all game objects
     */
    public void tick()
    {
        for (int i = 0; i < object.size(); i++)
        {
            tempObject = object.get(i);

            tempObject.tick(object);
        }
    }

    /**
     * Render the graphics
     * @param g graphic to draw
     */
    public void render(Graphics g)
    {
        for (int i = 0; i < object.size(); i++)
        {
            tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    /**
     * Add object to {@link Handler}
     * @param object object to add
     */
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }

    /**
     * Remove object from {@link Handler}
     * @param object object to remove
     */
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }

    public void createLevel() {
        for(int i = 0; i < Game.WIDTH + (32*20); i += 32) {
            addObject(new Block(i, Game.HEIGHT - 32, ObjectId.block));
//            addObject(new Block(Game.WIDTH - 32, i, ObjectId.block));
            addObject(new Block(0, i, ObjectId.block));
        }
    }
}
