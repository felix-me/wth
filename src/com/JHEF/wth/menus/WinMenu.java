package com.JHEF.wth.menus;

import com.JHEF.wth.window.Game;

import java.awt.*;

public class WinMenu {

    private Rectangle resetButton = new Rectangle(Game.WIDTH / 2 - 50, 400, 100, 55);
    private Rectangle menuButton = new Rectangle(Game.WIDTH / 2 - 100, 480, 200, 55);

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        Font titleFont = new Font("Rockwell", Font.BOLD, 80);
        g.setFont(titleFont);
        g.drawString("WUN.", Game.WIDTH / 2 - 80, 75);

        Font bodyFnt = new Font("Rockwell", Font.BOLD, 30);
        g.setFont(bodyFnt);
        g.drawString("Level One: "+(Game.levelOne-Game.levelTimer)+" Seconds", Game.WIDTH / 2 - 180, 110);
        g.drawString("Level Two: "+(Game.levelTwo-Game.levelOne)+" Seconds", Game.WIDTH / 2 - 180, 150);
        g.drawString("Level Three: "+(Game.levelThree-Game.levelTwo)+" Seconds", Game.WIDTH / 2 - 180, 190);
        g.drawString("Overall: "+(Game.levelThree-Game.levelTimer)+" Seconds", Game.WIDTH / 2 - 180, 230);
        g.drawString("Reset", resetButton.x+7, resetButton.y+40);
        g.drawString("Main Menu", menuButton.x+12, menuButton.y+40);
        g.setColor(Color.BLACK);
        g2d.draw(resetButton);
        g2d.draw(menuButton);

        g.dispose();
    }
}
