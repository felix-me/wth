package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {
    /**
     * constructor for {@link GameObject}
     *
     * @param x pos x
     * @param y pos y
     * @param id object id
     */
    public Block(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }


}
