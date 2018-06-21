package com.JHEF.wth.window;

import java.awt.*;

public class OptionsMenu {

    public static String muteOption = "Mute";

    public Rectangle muteButton = new Rectangle(Game.WIDTH /2 - 50, 200, 100, 55);
    public Rectangle keyMapButton = new Rectangle(Game.WIDTH /2 - 150, 280, 250, 55);
    public Rectangle backButton = new Rectangle(Game.WIDTH /2 - 50, 500, 100, 55);

    BufferedImageLoader bi = new BufferedImageLoader();

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        Font titleFont = new Font("Rockwell", Font.CENTER_BASELINE, 50);
        g.setFont(titleFont);
        g.drawString("Options", Game.WIDTH / 2 - 105, 100);

        Font btnFnt = new Font("Rockwell", Font.BOLD, 30);
        g.setFont(btnFnt);

        g.drawString(muteOption, muteButton.x+5, muteButton.y+40);
        g.drawString("Change Key Input", keyMapButton.x+5, keyMapButton.y+40);
        g.drawString("Back", backButton.x+5, backButton.y+40);
        g.setColor(Color.RED);
        g2d.draw(muteButton);
        g2d.draw(keyMapButton);
        g2d.draw(backButton);

        g.dispose();
    }
}
