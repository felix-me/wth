package com.JHEF.wth.window;

import com.JHEF.wth.framework.*;
import com.JHEF.wth.objects.Block;
import com.JHEF.wth.objects.Player;
import com.JHEF.wth.objects.Imp;
import com.JHEF.wth.objects.Player;
import com.JHEF.wth.framework.KeyInput;
import com.JHEF.wth.framework.MouseInput;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Texture;

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

    private MainMenu mainMenu;
    private OptionsMenu optionsMenu;
    private HelpMenu helpMenu;
    private DeathMenu deadMenu;
    private WinMenu winMenu;

    private static Game gameInstance;

    private long timer = System.currentTimeMillis();
    public static int levelNumber = 0;

    private BufferedImageLoader loader;
    private BufferedImage level;
    private BufferedImage[] background;

    public Game()
    {
        gameInstance=this;
        loader = new BufferedImageLoader();
        this.background = new BufferedImage[]{loader.loadImage("/hell_BG.gif"), loader.loadImage("/Earth_background.png"), loader.loadImage("/heaven_background.png"),loader.loadImage("/heaven_background.png")};
    }

    // Object
    Handler handler;
    Camera cam;
    static Texture tex;

    // Game states
    public enum STATE{
        GAME,
        MENU,
        HELP,
        OPTIONS,
        DEAD,
        WON
    };

    public static STATE state = STATE.MENU;

    private void init()
    {

        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/hell.png");

        cam = new Camera(0,0);
        handler = new Handler(cam);

        handler.loadImageLevel(level);

        mainMenu = new MainMenu();
        optionsMenu = new OptionsMenu();
        helpMenu = new HelpMenu();
        deadMenu = new DeathMenu();
        winMenu = new WinMenu();

//        handler.loadImageLevel(level);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());

        // Play Theme Tune
        Sound sound = new Sound();

        try {
            sound.playSound("/mainMenuTheme.wav");
        } catch (Exception e) {
            System.out.println(e);
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
//                System.out.println("FPS: " + frames + " TICKS: " + updates);
//                System.out.println("Delta: " + delta + " timer: " + timer);
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

        BufferedImage background = resize(this.background[levelNumber],700,900);
        

        g2d.translate(cam.getX(),cam.getY()); //begin of cam
        int xx = 0;
        do{
            g.drawImage(background,xx,0,this);
            xx += background.getWidth();
        } while(xx < background.getWidth() * 5);

        if (state == STATE.GAME) {
            handler.render(g);
        } else if (state == STATE.MENU) {
            mainMenu.render(g);
        } else if (state == STATE.OPTIONS) {
            optionsMenu.render(g);
        } else if (state == STATE.HELP) {
            helpMenu.render(g);
        } else if (state == STATE.DEAD) {
            cam = new Camera(0,0);
            deadMenu.render(g);
        } else if(state == STATE.WON) {
            cam = new Camera(0,0);
            winMenu.render(g);
        }

        g2d.translate(-cam.getX(),-cam.getY()); //end of cam

        // End of game draw
        ////////////////////////////////////////////

        g.dispose();
        bs.show();
    }

    public static Texture getTexture() {
        return tex;
    }

    public static Game getInstance() {
        return gameInstance;
    }

    public long getTimer() {
        return timer;
    }

    public void restartGame() {
        BufferedImageLoader loader = new BufferedImageLoader();
        Game.state = Game.STATE.DEAD;
        handler.object.clear();
        handler.loadImageLevel(loader.loadImage("/hell.png"));
        Game.levelNumber = 0;
    }

    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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
