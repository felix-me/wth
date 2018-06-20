package com.JHEF.wth.window;

import com.JHEF.wth.framework.KeyInput;
import com.JHEF.wth.framework.MouseInput;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.objects.Block;
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

    private MainMenu mainMenu;
    private OptionsMenu optionsMenu;
    private HelpMenu helpMenu;

    // Object
    Handler handler;
    Camera cam;

    // Game states
    public enum STATE{
        GAME,
        MENU,
        HELP,
        OPTIONS
    };

    public static STATE state = STATE.MENU;

    private void init()
    {

        WIDTH = getWidth();
        HEIGHT = getHeight();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/hellsquare.png");

        handler = new Handler();

        cam = new Camera(0,0);

        mainMenu = new MainMenu();
        optionsMenu = new OptionsMenu();
        helpMenu = new HelpMenu();

        loadImageLevel(level);

        //handler.addObject(new Player(100, 100, handler, ObjectId.player));

        //handler.createLevel();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());

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

                if(red != 255 || green != 255 || blue != 255) {
                    handler.addObject(new Block(xx*32,yy*32,ObjectId.block));
                }
                if(red == 0 && green == 0 && blue == 0) {
                    handler.addObject(new Player(xx*32,yy*32,handler,ObjectId.player));
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
        if(state == STATE.GAME) {
            handler.tick();
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getId() == ObjectId.player) {
                    cam.tick(handler.object.get(i));
                }
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

        if (state == STATE.GAME) {
            handler.render(g);
        } else if (state == STATE.MENU) {
            mainMenu.render(g);
        } else if (state == STATE.OPTIONS) {
            optionsMenu.render(g);
        } else if (state == STATE.HELP) {
            helpMenu.render(g);
        }

        g2d.translate(-cam.getX(),-cam.getY()); //end of cam

        // End of game draw
        ////////////////////////////////////////////

        g.dispose();
        bs.show();
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
