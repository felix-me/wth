package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Sound;
import com.JHEF.wth.window.Animation;
import com.JHEF.wth.window.Game;
import com.JHEF.wth.window.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;



public class Player extends GameObject {

    private float width = 32, height = 64;
    private float gravity = 0.62f;
    private int facing = 1;

    private Handler handler;
    private Animation playerWalk, playerWalkLeft, playerWithWings, playerWithWingsLeft;
    private ArrayList<Integer> killBlocks = new ArrayList<>();
    private ArrayList<Integer> powerUpBlocks = new ArrayList<>();

    private int powerUpRemaining = -1;

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

        playerWalk = new Animation(10, tex.player[1], tex.player[2]);
        playerWalkLeft = new Animation(10, tex.player[4], tex.player[5]);
        playerWithWings = new Animation(10,tex.playerWings[0], tex.playerWings[1], tex.playerWings[2]);
        playerWithWingsLeft = new Animation(10,tex.playerWings[3], tex.playerWings[4], tex.playerWings[5]);


        killBlocks.add(6);
        killBlocks.add(7);
        killBlocks.add(8);
        killBlocks.add(9);
        powerUpBlocks.add(22);
    }

    public void tick(LinkedList<GameObject> object) {
        if(powerUpRemaining == 0) {
            gravity=0.62f;
            powerUpRemaining --;
        } else if(powerUpRemaining != -1) {
            powerUpRemaining--;
        }
        x += velX;
        y += velY;

        if(velX > 0) facing =1;
        else if(velX < 0) facing = -1;
        if (falling || jumping) {
            velY += gravity;

            float MAX_SPEED = 7;
            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }

        Collision();
        playerWalk.runAnimation();
        playerWalkLeft.runAnimation();
        playerWithWings.runAnimation();
        playerWithWingsLeft.runAnimation();
    }

    private void doesCollide(GameObject tempObject, int ix) {
        if(tempObject instanceof Block) {
            Sound sound = new Sound();
            Block blockCollided = (Block) tempObject;
            if(killBlocks.contains(blockCollided.getType())) {
                handler.sound.killSound();
                sound.playSound("/devilDeathNoiseConverted.wav", false);

                Game.getInstance().restartGame();

            } else if(powerUpBlocks.contains(blockCollided.getType())) {
                handler.removeObject(handler.object.get(ix));
                gravity = 0.3f;
                powerUpRemaining = 500;
            }
        }
    }

    private void Collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ObjectId.block) {

                if (getBoundsTop().intersects(tempObj.getBounds())) {
                    y = tempObj.getY()+9;
                    velY = 0;
                    doesCollide(tempObj,i);
                }

                if (getBoundsBottom().intersects(tempObj.getBounds())) {
                    y = tempObj.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                    doesCollide(tempObj,i);
                }
                else {
                    falling = true;
                }

                if (getBoundsRight().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() - width;
                    doesCollide(tempObj,i);
                }

                if (getBoundsLeft().intersects(tempObj.getBounds())) {
                    x = tempObj.getX() + width;
                    doesCollide(tempObj,i);
                }
            } else if(tempObj.getId() == ObjectId.flag) {
                //switch level
                if(getBounds().intersects(tempObj.getBounds())) {
                    handler.switchLevel();
                    Game.levelNumber++;
                }
            }

            else if (tempObj.getId() == ObjectId.imp) {
                if (getBounds().intersects(tempObj.getBoundsLeft()) || getBounds().intersects(tempObj.getBoundsRight())) {
                    Game.getInstance().restartGame();
                }
                if (getBoundsBottom().intersects(tempObj.getBoundsTop())) {
                    handler.removeObject(tempObj);
                }
            }

        }

    }

    public void render(Graphics g) {
        if(jumping){

            if(facing == 1){
                if(powerUpRemaining != -1){
                    playerWithWings.drawAnimation(g,(int)x,(int)y);
                } else g.drawImage(tex.playerJump[0],(int)x,(int)y, 32,64,null);
            } else if(facing == -1){
                if(powerUpRemaining != -1) playerWithWingsLeft.drawAnimation(g,(int)x,(int)y);
                else g.drawImage(tex.playerJump[1],(int)x,(int)y, 32,64,null);
            }

        } else{
            if(velX != 0){
                if(facing == 1) {
                    if(powerUpRemaining != -1){
                        playerWithWings.drawAnimation(g,(int)x,(int)y);
                    }
                    else playerWalk.drawAnimation(g,(int)x,(int)y);
                }
                else {
                    if(powerUpRemaining != -1) playerWithWingsLeft.drawAnimation(g,(int)x,(int)y);
                    else playerWalkLeft.drawAnimation(g,(int)x,(int)y);
                }
            } else {
                if(powerUpRemaining != -1){

                    g.drawImage(tex.playerWings[0], (int)x,(int)y,32,64,null);
                } else g.drawImage(tex.player[0], (int) x, (int) y, null);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x + 5,(int)y+23,24,40);
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
