package com.JHEF.wth.window;

import javax.swing.*;
import java.awt.*;

/**
 * @author WTH
 * @version 1.0
 * Class to handle all {@link JFrame} related components.
 */
public class Window
{
    /**
     * Class constructor creating {@link JFrame} which contains all game elements.
     * Runs {@link Game} start() method.
     *
     * @param w the width of the window
     * @param h the height of the window
     * @param title the title of the window
     * @param game which {@link Game} instance to instantiate
     */
    public Window(int w, int h, String title, Game game)
    {
        game.setPreferredSize(new Dimension(w, h));
        game.setMaximumSize(new Dimension(w, h));
        game.setMinimumSize(new Dimension(w, h));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }
}
