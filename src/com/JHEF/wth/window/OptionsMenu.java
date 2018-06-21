package com.JHEF.wth.window;

import com.JHEF.wth.framework.KeyInput;

import java.awt.*;

public class OptionsMenu {

    private Rectangle muteButton = new Rectangle(Game.WIDTH / 2 - 50, 150, 100, 55);
    private Rectangle leftArrow = new Rectangle(Game.WIDTH / 2 - 150, 230, 350, 55);
    private Rectangle rightArrow = new Rectangle(Game.WIDTH / 2 - 150, 310, 350, 55);
    private Rectangle upArrow = new Rectangle(Game.WIDTH / 2 - 150, 390, 350, 55);

    private Rectangle backButton = new Rectangle(Game.WIDTH / 2 - 50, 450, 100, 55);
    public static String muteOption = "Mute";

    private BufferedImageLoader bi = new BufferedImageLoader();
    private Image leftArrowImg = bi.loadImage("/left_control.png");
    private Image rightArrowImg = bi.loadImage("/right_control.png");
    private Image upArrowImg = bi.loadImage("/up_control.png");

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        Font titleFont = new Font("Rockwell", Font.BOLD, 50);
        g.setFont(titleFont);
        g.drawString("Options", Game.WIDTH / 2 - 105, 50);

        Font btnFnt = new Font("Rockwell", Font.BOLD, 20);
        g.setFont(btnFnt);
        g.drawString(muteOption, muteButton.x+5, muteButton.y+40);
        g.drawImage(leftArrowImg, Game.WIDTH / 2 - 130, 220, null);
        g.drawImage(rightArrowImg, Game.WIDTH / 2 - 130, 300, null);
        g.drawImage(upArrowImg, Game.WIDTH / 2 - 130, 380, null);

        g.drawString("Current Control is: " + (KeyInput.getLeft()), leftArrow.x+110, leftArrow.y + 30);
        g.drawString("Current Control is: " + (KeyInput.getRight()), rightArrow.x+110, rightArrow.y + 30);
        g.drawString("Current Control is: " + (KeyInput.getUp()), upArrow.x+110, upArrow.y + 30);
        g.drawString("To change controls click on the icon and press desired key", Game.WIDTH / 2 - 285, 100);
        g.drawString("Back", backButton.x+5, backButton.y + 30);
        g.setColor(Color.RED);
        g2d.draw(muteButton);
        g2d.draw(leftArrow);
        g2d.draw(rightArrow);
        g2d.draw(upArrow);
        g2d.draw(backButton);

        g.dispose();
    }
}
