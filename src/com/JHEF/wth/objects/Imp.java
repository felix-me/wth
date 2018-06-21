package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.window.Game;
import com.JHEF.wth.window.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Imp extends GameObject {


    private float width = 48, height = 48;

    private int prevTime = ((int) System.currentTimeMillis() / 1000);

    private Handler handler;

    /**
     * constructor for {@link GameObject}
     *
     * @param x  pos x
     * @param y  pos y
     * @param id objectId
     */
    public Imp(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        setVelX(0.5f);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;
        int seconds = ((int) System.currentTimeMillis() / 1000);
        if ((seconds != prevTime) && (seconds % 2 == 0)) {
            setVelX(-1 * velX);
        }

        if ((seconds != prevTime) && (seconds % 4 == 0)) {
            setVelY(-10);
        }

        prevTime = seconds;

        if (falling || jumping) {
            float gravity = 0.62f;
            velY += gravity;
        }

        Collision();
    }

    private void Collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ObjectId.block) {

                if (getBoundsTop().intersects(tempObj.getBounds())) {
                    y = tempObj.getY() + 9;
                    velY = 0;
                }

                if (getBoundsBottom().intersects(tempObj.getBounds())) {
                    y = tempObj.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }

                if (getBoundsRight().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() - width;
                }

                if (getBoundsLeft().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() + width;
                }

            }

        }

    }

    public void render(Graphics g) {
        BufferedImage img = Game.resize(tex.imp[0], 48, 48);
        g.drawImage(img, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return null;
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + (int) width / 2 - (int) (width / 2) / 3, (int) y + 12, ((int) width / 2 - 10), ((int) height / 2) - 15);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle((int) x + (int) width / 2 - (int) (width / 2) / 3, ((int) y + (int) height), ((int) width / 2 - 2), ((int) height / 2) - 15);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + (int) width / 2 - (int) (width / 2) / 2, (int) y + 25, 5, (int) height - 30);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + (int) width / 2 - (int) (width / 2) + 30, (int) y + 25, 5, (int) height - 30);
    }
}
