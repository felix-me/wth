package com.JHEF.wth.window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WinMenu {

    public Rectangle resetButton = new Rectangle(Game.WIDTH /2 - 50, 400, 100, 55);
    public Rectangle menuButton = new Rectangle(Game.WIDTH /2 - 100, 480, 200, 55);

    BufferedImageLoader bi = new BufferedImageLoader();

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        Image logo = bi.loadImage("/win.jpg");
        BufferedImage image = resize((BufferedImage)logo, 400, 500);
        g.drawImage(image, Game.WIDTH / 2 - 250, 60, null);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        Font titleFont = new Font("Rockwell", Font.CENTER_BASELINE, 80);
        g.setFont(titleFont);
        g.drawString("WUN.", Game.WIDTH / 2 - 80, 75);

        Font bodyFnt = new Font("Rockwell", Font.CENTER_BASELINE, 30);
        g.setFont(bodyFnt);
//        g.drawString("You have escaped from hell and made it to heaven", Game.WIDTH / 2 - 220, 110);

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