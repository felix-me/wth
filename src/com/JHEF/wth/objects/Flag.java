package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Flag extends GameObject {

    public Flag(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.drawImage(tex.levelEnd[0], (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    public Rectangle getBoundsTop() {
        return null;
    }
    public Rectangle getBoundsBottom() {
        return null;
    }
    public Rectangle getBoundsLeft() {
        return null;
    }
    public Rectangle getBoundsRight() {
        return null;
    }

}
