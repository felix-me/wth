package com.JHEF.wth.framework;

import com.JHEF.wth.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ObjectId.player) {
                if (key == KeyEvent.VK_W  && !tempObj.isJumping()) {
                    tempObj.setJumping(true);
                    tempObj.setVelY(-10);
                }
                if (key == KeyEvent.VK_A) {
                    tempObj.setVelX(-5);
                }
                if (key == KeyEvent.VK_S) {
                    //                    Crouch
                }
                if (key == KeyEvent.VK_D) {
                    tempObj.setVelX(5);
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ObjectId.player) {
                if (key == KeyEvent.VK_W) {
                    tempObj.setVelY(0);
                }
                if (key == KeyEvent.VK_A) {
                    tempObj.setVelX(0);
                }
                if (key == KeyEvent.VK_S) {
                    //                    Crouch
                }
                if (key == KeyEvent.VK_D) {
                    tempObj.setVelX(0);
                }
            }
        }
    }
}
