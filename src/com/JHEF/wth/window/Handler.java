package com.JHEF.wth.window;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Sound;
import com.JHEF.wth.objects.Block;
import com.JHEF.wth.objects.Flag;
import com.JHEF.wth.objects.Imp;
import com.JHEF.wth.objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Handles game objects and stores them
 */
public class Handler {

    public LinkedList<GameObject> object = new LinkedList<>();

    private GameObject tempObject;

    private Camera cam;
    private BufferedImageLoader loader;

    public Sound sound = new Sound();

    public Handler(Camera cam) {
        this.cam = cam;
        loader = new BufferedImageLoader();
    }

    /**
     * Per tick get all game objects
     */
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);

            tempObject.tick(object);
        }
    }

    /**
     * Render the graphics
     *
     * @param g graphic to draw
     */
    public void render(Graphics g) {
        for (GameObject anObject : object) {
            tempObject = anObject;
            tempObject.render(g);
        }
    }

    /**
     * Add object to {@link Handler}
     *
     * @param object object to add
     */
    private void addObject(GameObject object) {
        this.object.add(object);
    }

    /**
     * Remove object from {@link Handler}
     *
     * @param object object to remove
     */
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    private void clearLevel() {
        object.clear();
    }

    void loadImageLevel(BufferedImage image) {

        int h = image.getHeight();
        int w = image.getWidth();

        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                int number = (int) (Math.random() * 100);
                if (red == 100 && green == 0 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 0, ObjectId.block));
                }
                else if (red == 255 && green == 0 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 1, ObjectId.block));
                }
                else if (red == 200 && green == 0 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 2, ObjectId.block));
                }
                else if (red == 150 && green == 0 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 3, ObjectId.block));
                }
                else if (red == 50 && green == 0 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 4, ObjectId.block));
                }
                else if (red == 25 && green == 0 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 5, ObjectId.block));
                }
                else if (red == 0 && green == 0 && blue == 255) {
                    addObject(new Block(xx * 32, yy * 32, 6, ObjectId.block));
                }
                else if (red == 0 && green == 0 && blue == 200) {
                    addObject(new Block(xx * 32, yy * 32, 7, ObjectId.block));
                }
                else if (red == 0 && green == 0 && blue == 150) {
                    addObject(new Block(xx * 32, yy * 32, 8, ObjectId.block));
                }
                else if (red == 0 && green == 0 && blue == 100) {
                    addObject(new Block(xx * 32, yy * 32, 9, ObjectId.block));
                }
                else if (red == 0 && green == 255 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 10, ObjectId.block));
                }
                else if (red == 0 && green == 230 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 11, ObjectId.block));
                }
                else if (red == 0 && green == 210 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 12, ObjectId.block));
                }
                else if (red == 0 && green == 190 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 13, ObjectId.block));
                }
                else if (red == 0 && green == 170 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 14, ObjectId.block));
                }
                else if (red == 0 && green == 150 && blue == 0) {
                    addObject(new Block(xx * 32, yy * 32, 15, ObjectId.block));
                }
                else if (red == 255 && green == 0 && blue == 255) {
                    addObject(new Block(xx * 32, yy * 32, 16, ObjectId.block));
                }
                else if (red == 255 && green == 0 && blue == 200) {
                    addObject(new Block(xx * 32, yy * 32, 17, ObjectId.block));
                }
                else if (red == 255 && green == 0 && blue == 150) {
                    addObject(new Block(xx * 32, yy * 32, 18, ObjectId.block));
                }
                else if (red == 255 && green == 0 && blue == 100) {
                    addObject(new Block(xx * 32, yy * 32, 19, ObjectId.block));
                }
                else if (red == 255 && green == 0 && blue == 80) {
                    addObject(new Block(xx * 32, yy * 32, 20, ObjectId.block));
                }
                else if (red == 255 && green == 0 && blue == 60) {
                    addObject(new Block(xx * 32, yy * 32, 21, ObjectId.block));
                }
                else if (red == 0 && green == 0 && blue == 20) {
                    addObject(new Flag(xx * 32, yy * 32, ObjectId.flag));
                }
                else if (red == 0 && green == 0 && blue == 0) {
                    addObject(new Player(xx * 32, (yy * 32), this, ObjectId.player));
                }
                else if(red == 255 && green == 100 && blue == 255) {
                    if(number < 40) {
                        addObject(new Imp(xx * 32, yy * 32, this, ObjectId.imp));
                    }
                }
                else if (red == 255 && green == 255 && blue == 0) {
                    if(number > 75) {
                        addObject(new Block(xx * 32, yy * 32, 22, ObjectId.block));
                    }
                }
            }
        }

    }

    public void switchLevel() {
        clearLevel();
        cam.setX(0);
        switch (Game.levelNumber)
        {
            case 0:
                Game.getInstance().getThemeTune().killSound();
                sound.playSound("/earthTheme.wav", true);
                Game.levelOne = System.currentTimeMillis()/1000;
                loadImageLevel(loader.loadImage("/earth.png"));
                break;
            case 1:
                sound.killSound();
                sound.playSound("/heavenTheme.wav", true);
                Game.levelTwo = System.currentTimeMillis()/1000;
                loadImageLevel(loader.loadImage("/heaven.png"));
                break;
            case 2:
                sound.killSound();
                sound.playSound("/applause.wav", false);
                sound.playSound("/winSong.wav", true);
                Game.levelThree = System.currentTimeMillis()/1000;
                Game.state = Game.STATE.WON;
                break;
        }
    }

    public Sound getSound() {
        return sound;
    }


}
