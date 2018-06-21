package com.JHEF.wth.window;

import com.JHEF.wth.framework.*;

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

    public static int WIDTH, HEIGHT;

    private MainMenu mainMenu;
    private OptionsMenu optionsMenu;
    private HelpMenu helpMenu;
    private DeathMenu deadMenu;
    private WinMenu winMenu;

    private static Game gameInstance;

    static long levelTimer = System.currentTimeMillis() / 1000;
    static long levelOne;
    static long levelTwo;
    static long levelThree;
  
    private boolean muted = false;

    public static int levelNumber = 0;

    private BufferedImage[] background;

    // Play Theme Tune
    private Sound themeTune = new Sound();
    private static Texture tex;
    // Object
    private Handler handler;

    private Camera cam;
    public Game()
    {
        gameInstance=this;
        BufferedImageLoader loader = new BufferedImageLoader();
        this.background = new BufferedImage[]{loader.loadImage("/hell_BG.gif"), loader.loadImage("/Earth_background.png"), loader.loadImage("/heaven_background.png"), loader.loadImage("/heaven_background.png")};
    }

    private void init()
    {

        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage level = loader.loadImage("/hell.png");

        cam = new Camera(0,0);
        handler = new Handler(cam);

        handler.loadImageLevel(level);

        mainMenu = new MainMenu();
        optionsMenu = new OptionsMenu();
        helpMenu = new HelpMenu();
        deadMenu = new DeathMenu();
        winMenu = new WinMenu();
        KeyInput keyInput = new KeyInput(handler);

//        handler.loadImageLevel(level);

        this.addKeyListener(keyInput);
        this.addMouseListener(new MouseInput());

        themeTune.playSound("/mainMenuTheme.wav", true);

    }

    public static STATE state = STATE.MENU;

    /**
     * Handles all instances when game is started.
     *
     * Creates a {@link Thread}
     */
    public synchronized void start()
    {
        /*
          Make sure that thread is not already running
         */
        if (running)
            return;

        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public boolean isNotMuted() {
        return !muted;
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
        long timer = System.currentTimeMillis();
        double delta = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while (delta >= 1)
            {
                tick();
                delta--;
            }
            render();

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
//                System.out.println("FPS: " + frames + " TICKS: " + updates);
//                System.out.println("Delta: " + delta + " timer: " + timer);
            }
        }
    }

    /**
     * Updates to the system
     */
    public void tick()
    {
        if(state == STATE.GAME) {
            if(levelTimer == -1) {
                levelTimer = System.currentTimeMillis() / 1000;
            }
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

    // Game states
    public enum STATE {
        GAME,
        MENU,
        HELP,
        OPTIONS,
        DEAD,
        WON
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public Sound getThemeTune() {
        return themeTune;
    }

    public void restartGame() {
        BufferedImageLoader loader = new BufferedImageLoader();
        Game.state = Game.STATE.DEAD;
        handler.object.clear();
        handler.loadImageLevel(loader.loadImage("/hell.png"));
        Game.levelNumber = 0;
        Game.levelTimer = -1;
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
