package com.JHEF.wth.window;

import java.awt.*;

public class HelpMenu {

    public Rectangle backButton = new Rectangle(Game.WIDTH /2 - 50, 520, 100, 55);

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        Font titleFont = new Font("Rockwell", Font.CENTER_BASELINE, 50);
        g.setFont(titleFont);
        g.drawString("Instructions", Game.WIDTH / 2 - 150, 100);

        Font bodyFnt = new Font("Rockwell", Font.CENTER_BASELINE, 30);
        g.setFont(bodyFnt);
        g.drawString("-> Use Keys to move Ghost through the map", Game.WIDTH / 2 - 350, 150);
        g.drawString("-> Avoid obstacles and enemies", Game.WIDTH / 2 - 350, 200);
        g.drawString("-> Reach the end of each level and escape Hell", Game.WIDTH / 2 - 350, 250);
        g.drawString("-> Find your way to Heaven", Game.WIDTH / 2 - 350, 300);
        g.drawString("-> If you die, you will be dragged back down to", Game.WIDTH / 2 - 350, 350);
        g.drawString("     Hell", Game.WIDTH / 2 - 350, 400);
        g.drawString("-> Enjoy!", Game.WIDTH / 2 - 350, 450);

        g.drawString("Back", backButton.x+5, backButton.y+40);
        g.setColor(Color.RED);
        g2d.draw(backButton);

        g.dispose();
    }
}
