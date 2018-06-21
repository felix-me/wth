package com.JHEF.wth.menus;

import com.JHEF.wth.window.BufferedImageLoader;
import com.JHEF.wth.window.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DeathMenu {

    private Rectangle resetButton = new Rectangle(Game.WIDTH / 2 - 50, 400, 100, 55);
    private Rectangle menuButton = new Rectangle(Game.WIDTH / 2 - 100, 480, 200, 55);

    private BufferedImageLoader bi = new BufferedImageLoader();

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        Image logo = bi.loadImage("/DedGhost.png");
        BufferedImage image = resize((BufferedImage)logo, 400, 500);
        g.drawImage(image, Game.WIDTH / 2 - 250, 60, null);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        Font titleFont = new Font("Rockwell", Font.BOLD, 80);
        g.setFont(titleFont);
        g.drawString("ded.", Game.WIDTH / 2 - 80, 75);

        Font bodyFnt = new Font("Rockwell", Font.BOLD, 30);
        g.setFont(bodyFnt);
        g.drawString("You have been returned to Hell", Game.WIDTH / 2 - 220, 110);

        g.drawString("Reset", resetButton.x+7, resetButton.y+40);
        g.drawString("Main Menu", menuButton.x+12, menuButton.y+40);
        g.setColor(Color.BLACK);
        g2d.draw(resetButton);
        g2d.draw(menuButton);

        g.dispose();
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
