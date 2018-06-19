package com.JHEF.wth.window;

import java.awt.*;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    public synchronized void start()
    {
        // Make sure that thread is not already running
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        System.out.println("Thread has begun");
    }

    public static void main(String args[])
    {
        new Window(800, 600, "What The Hell!?", new Game());
    }
}
