package com.JHEF.wth.window;

import com.JHEF.wth.framework.KeyInput;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Texture;
import com.JHEF.wth.objects.Block;
import com.JHEF.wth.objects.Enemy;
import com.JHEF.wth.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * @author WTH
 * @version 1.0
 * Creates {@link Thread} and starts instance.
 * Also Contains main method.
 */
public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;

    private BufferedImage level = null;


    // Object
    Handler handler;
    Camera cam;
    static Texture tex;

    private void init()
    {

        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/hell.png");

        handler = new Handler();

        cam = new Camera(0,0);

        loadImageLevel(level);

        this.addKeyListener(new KeyInput(handler));
    }

    private void loadImageLevel(BufferedImage image){

        int h = image.getHeight();
        int w = image.getWidth();

        System.out.println(h);
        System.out.println(w);

        for(int xx = 0; xx < h; xx++) {
            for(int yy = 0; yy < w; yy++) {
                System.out.println(xx);
                System.out.println(yy);
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 100 && green == 0 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,0,ObjectId.block));
                }
                if(red == 255 && green == 0 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,1,ObjectId.block));
                }
                if(red == 200 && green == 0 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,2,ObjectId.block));
                }
                if(red == 150 && green == 0 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,3,ObjectId.block));
                }
                if(red == 50 && green == 0 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,4,ObjectId.block));
                }
                if(red == 25 && green == 0 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,5,ObjectId.block));
                }
                if(red == 0 && green == 0 && blue == 255) {
                    handler.addObject(new Block(xx*32,yy*32,6,ObjectId.block));
                }
                if(red == 0 && green == 0 && blue == 200) {
                    handler.addObject(new Block(xx*32,yy*32,7,ObjectId.block));
                }
                if(red == 0 && green == 0 && blue == 150) {
                    handler.addObject(new Block(xx*32,yy*32,8,ObjectId.block));
                }
                if(red == 0 && green == 0 && blue == 100) {
                    handler.addObject(new Block(xx*32,yy*32,9,ObjectId.block));
                }
                if(red == 0 && green == 255 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,10,ObjectId.block));
                }
                if(red == 0 && green == 230 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,11,ObjectId.block));
                }
                if(red == 0 && green == 210 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,12,ObjectId.block));
                }
                if(red == 0 && green == 190 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,13,ObjectId.block));
                }
                if(red == 0 && green == 170 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,14,ObjectId.block));
                }
                if(red == 0 && green == 150 && blue == 0) {
                    handler.addObject(new Block(xx*32,yy*32,15,ObjectId.block));
                }
                if(red == 255 && green == 0 && blue == 255) {
                    handler.addObject(new Block(xx*32,yy*32,16,ObjectId.block));
                }
                if(red == 255 && green == 0 && blue == 200) {
                    handler.addObject(new Block(xx*32,yy*32,17,ObjectId.block));
                }
                if(red == 255 && green == 0 && blue == 150) {
                    handler.addObject(new Block(xx*32,yy*32,18,ObjectId.block));
                }
                if(red == 255 && green == 0 && blue == 100) {
                    handler.addObject(new Block(xx*32,yy*32,19,ObjectId.block));
                }
                if(red == 255 && green == 0 && blue == 80) {
                    handler.addObject(new Block(xx*32,yy*32,20,ObjectId.block));
                }
                if(red == 255 && green == 0 && blue == 60) {
                    handler.addObject(new Block(xx*32,yy*32,21,ObjectId.block));
                }
                if(red == 0 && green == 0 && blue == 0) {
                    handler.addObject(new Player(xx*32,yy*32,handler,ObjectId.player));
                }
                if(red == 255 && green == 100 && blue == 255) {
                    handler.addObject(new Enemy(xx*32,yy*32,handler,ObjectId.imp));
                }
            }
        }

    }

    /**
     * Handles all instances when game is started.
     *
     * Creates a {@link Thread}
     */
    public synchronized void start()
    {
        /**
         * Make sure that thread is not already running
         */
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Overridden method which runs when {@link Runnable} is instantiated
     */
    public void run()
    {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0; // FPS
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while (delta >= 1)
            {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    /**
     * Updates to the system
     */
    public void tick()
    {
        handler.tick();
        for(int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ObjectId.player) {
                cam.tick(handler.object.get(i));
            }
        }
    }

    /**
     * Graphical components
     * Images
     * Backgrounds
     * Sprites
     * Limit FPS here through {@link BufferStrategy}
     */
    public void render()
    {
        BufferStrategy bs = this.getBufferStrategy(); // From Canvas
        // Make sure that buffer is not always set to null when render called
        if(bs == null)
        {
            this.createBufferStrategy(3); // load in 3 buffers if possible
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ////////////////////////////////////////////

        // Draw game here

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight()); // Stop flickering

        g2d.translate(cam.getX(),cam.getY()); //begin of cam

        handler.render(g);

        g2d.translate(-cam.getX(),-cam.getY()); //end of cam

        // End of game draw
        ////////////////////////////////////////////

        g.dispose();
        bs.show();
    }

    public static Texture getInstance() {
        return tex;
    }

    /**
     * Main method for running WTH.
     * @param args standard main method param
     */
    public static void main(String args[])
    {
        new Window(800, 600, "What The Hell!?", new Game());
    }
}
