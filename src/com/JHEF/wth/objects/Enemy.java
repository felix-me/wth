package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Texture;
import com.JHEF.wth.window.Game;
import com.JHEF.wth.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Enemy extends GameObject {


    private float width = 32, height = 64;
    private float gravity = 0.62f;

    private Handler handler;

    Texture tex = Game.getInstance();

    /**
     * constructor for {@link GameObject}
     *
     * @param x  pos x
     * @param y  pos y
     * @param id objectId
     */
    public Enemy(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if (falling || jumping) {
            velY += gravity;
        }

        Collision(object);
    }

    private void Collision(LinkedList<GameObject> object) {

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

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawImage(tex.player[0],(int)x,(int)y,null);

//        Graphics2D g2d = (Graphics2D) g;
//
//        g.setColor(Color.RED);
//
//        g2d.draw(getBoundsBottom());
//        g2d.draw(getBoundsRight());
//        g2d.draw(getBoundsLeft());
//        g2d.draw(getBoundsTop());

    }

    public Rectangle getBounds() {
        return null;
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle((int)x + (int)width/2 - (int)(width/2)/2, (int)y + (int)(height/2), (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int)x + (int)width/2 - (int)(width/2)/2, (int)y+23, (int)width/2, ((int)height/2)-23);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int)x + (int)(width-5), (int)y+23, 5, (int)height - 23);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y+23, 5, (int)height - 23);
    }
}
