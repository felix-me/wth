package com.JHEF.wth.menus;

import com.JHEF.wth.utils.GraphicsUtils;
import com.JHEF.wth.window.BufferedImageLoader;
import com.JHEF.wth.window.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu {

    private Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 60, 200, 120, 55);
    private Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 60, 280, 125, 55);
    private Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 60, 440, 120, 55);
    private Rectangle optionsButton = new Rectangle(Game.WIDTH / 2 - 100, 360, 205, 55);

    private BufferedImageLoader bi = new BufferedImageLoader();

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        Image logo = bi.loadImage("/logo.png");
        BufferedImage image = GraphicsUtils.resize((BufferedImage)logo, 200, 680);
        g.drawImage(image, Game.WIDTH / 2 - 320, 15, null);
        Graphics2D g2d = (Graphics2D) g;

        Font btnFnt = new Font("Rockwell", Font.BOLD, 50);
        g.setFont(btnFnt);
        g.setColor(Color.BLACK);
        g.drawString("Play", playButton.x+5, playButton.y+40);
        g.drawString("Options", optionsButton.x+5, optionsButton.y+40);
        g.drawString("Help", helpButton.x+5, helpButton.y+40);
        g.drawString("Quit", quitButton.x+5, quitButton.y+40);
        g.setColor(Color.RED);
        g2d.draw(playButton);
        g2d.draw(optionsButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);

        g.dispose();
    }

}
