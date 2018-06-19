package com.JHEF.wth.window;

import java.awt.*;

/**
 * @author WTH
 * @version 1.0
 * Creates {@link Thread} and starts instance.
 * Also Contains main method.
 */
public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

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
        System.out.println("Thread has begun");
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
