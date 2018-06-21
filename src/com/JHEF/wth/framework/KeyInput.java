package com.JHEF.wth.framework;

import com.JHEF.wth.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyInput extends KeyAdapter {

    Handler handler;
    public static int keyToChange = -1;
    private static int right = KeyEvent.VK_D;
    private static int left = KeyEvent.VK_A;
    private static int up = KeyEvent.VK_W;

    public static String getRight() {
        return KeyEvent.getKeyText(right);
    }

    public static String getLeft() {
        return KeyEvent.getKeyText(left);
    }

    public static String getUp() {
        return KeyEvent.getKeyText(up);
    }

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(keyToChange != -1){

            switch (keyToChange){

                case 0:
                    this.left = key;

                    System.out.println("Changed left");
                    break;

                case 1:
                    this.up = key;
                    System.out.println("Changed up");
                    break;

                case 2:
                    this.right = key;
                    System.out.println("Changed right");

            }

            this.keyToChange = -1;
        } else {


            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObj = handler.object.get(i);

                if (tempObj.getId() == ObjectId.player) {
                    if (key == up && !tempObj.isJumping()) {
                        tempObj.setJumping(true);
                        tempObj.setVelY(-10);
                    }
                    if (key == left) {
                        tempObj.setVelX(-5);
                    }

                    if (key == right) {
                        tempObj.setVelX(5);
                    }
                }
            }

            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(1);
            }

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size() ; i++) {
            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ObjectId.player) {
                if (key == up) {
                    tempObj.setVelY(0);
                }
                if (key == left) {
                    tempObj.setVelX(0);
                }
                if (key == right) {
                    tempObj.setVelX(0);
                }
            }
        }
    }

    public void setControls(String control){

        int key = 1;

        System.out.println("Being Called");

        try {
            System.out.println(System.in.read());
            key = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
