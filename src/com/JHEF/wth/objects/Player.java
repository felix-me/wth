package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Texture;
import com.JHEF.wth.window.BufferedImageLoader;
import com.JHEF.wth.window.Game;
import com.JHEF.wth.window.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 32, height = 64;
    private float gravity = 0.62f;
    private final float MAX_SPEED = 7;

    private Handler handler;

    private ArrayList<Integer> killBlocks = new ArrayList<>();

    Texture tex = Game.getTexture();

    /**
     * constructor for {@link GameObject}
     *
     * @param x  pos x
     * @param y  pos y
     * @param id objectId
     */
    public Player(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        killBlocks.add(6);
        killBlocks.add(7);
        killBlocks.add(8);
        killBlocks.add(9);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }

        Collision(object);
    }

    private void doesCollide(GameObject tempObject) {
        if(tempObject instanceof Block) {
            Block blockCollided = (Block) tempObject;
            if(killBlocks.contains(((Block) tempObject).getType())) {
                BufferedImageLoader loader = new BufferedImageLoader();
                Game.state = Game.STATE.DEAD;
                for (int i = 0; i < handler.object.size(); i++) {
                    if (handler.object.get(i).getId() == ObjectId.player) {
                        handler.removeObject(handler.object.get(i));
                    }
                }
                Game.getInstance().loadImageLevel(loader.loadImage("/hell.png"));
                System.out.println("die");
            }
        }
    }

    private void Collision(LinkedList<GameObject> object) {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ObjectId.block) {

                if (getBoundsTop().intersects(tempObj.getBounds())) {
                    y = tempObj.getY()+9;
                    velY = 0;
                    doesCollide(tempObj);
                }

                if (getBoundsBottom().intersects(tempObj.getBounds())) {
                    y = tempObj.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                    doesCollide(tempObj);
                }
                else {
                    falling = true;
                }

                if (getBoundsRight().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() - width;
                    doesCollide(tempObj);
                }

                if (getBoundsLeft().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() + width;
                    doesCollide(tempObj);
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
