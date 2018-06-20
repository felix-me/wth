package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Texture;
import com.JHEF.wth.window.BufferedImageLoader;
import com.JHEF.wth.window.Game;
import com.JHEF.wth.window.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Imp extends GameObject {


    private float width = 48, height = 48;
    private float gravity = 0.62f;

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
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if (falling || jumping) {
            velY += gravity;
        }

        Collision();
    }

    private void Collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ObjectId.block) {

                if (getBoundsTop().intersects(tempObj.getBounds())) {
                    y = tempObj.getY()+9;
                    velY = 0;
                }

                if (getBoundsBottom().intersects(tempObj.getBounds())) {
                    y = tempObj.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
                else {
                    falling = true;
                }

                if (getBoundsRight().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() - width;
                }

                if (getBoundsLeft().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() + width;
                }

            }

            else if (tempObj.getId() == ObjectId.player) {
                if (getBoundsLeft().intersects(tempObj.getBounds()) || getBoundsRight().intersects(tempObj.getBounds())) {
                    handler.removeObject(tempObj);
                }
            }

        }

    }

    public void render(Graphics g) {
        BufferedImage img = Game.resize(tex.imp[0], 48, 48);
        g.drawImage(img,(int)x,(int)y,null);

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.RED);

        g2d.draw(getBoundsBottom());

        g.setColor(Color.BLUE);
        g2d.draw(getBoundsTop());

        g.setColor(Color.YELLOW);
        g2d.draw(getBoundsLeft());

        g.setColor(Color.GREEN);
        g2d.draw(getBoundsRight());
    }

    public Rectangle getBounds() {
        return null;
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int)x + (int)width/2 - (int)(width/2)/3, (int)y + 20, ((int)width/2-10), ((int)height/2)-15);
    }
    public Rectangle getBoundsBottom() {
        return new Rectangle((int)x + (int)width/2 - (int)(width/2)/3, ((int)y + (int)height), ((int)width/2-2), ((int)height/2)-15);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x + (int)width/2 - (int)(width/2)/2, (int)y+25, 5, (int)height - 30);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int)x + (int)width/2 - (int)(width/2)+30, (int)y+25, 5, (int)height - 30);
    }
}
