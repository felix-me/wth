package com.JHEF.wth.framework;

import com.JHEF.wth.window.Game;
import com.JHEF.wth.window.OptionsMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //////// MAIN MENU /////////
        if (Game.state == Game.STATE.MENU) {
            // Play button
            if (mx >= Game.WIDTH / 2 - 60 && mx <= Game.WIDTH / 2 + 60) {
                if (my >= 200 && my <= 255) {
                    // Pressed Play Button
                    Game.state = Game.STATE.GAME;
                }
            }
            // Help button
            if (mx >= Game.WIDTH / 2 - 60 && mx <= Game.WIDTH / 2 + 65) {
                if (my >= 280 && my <= 335) {
                    // Pressed Help Button
                    Game.state = Game.STATE.HELP;
                }
            }
            // Options button
            if (mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 105) {
                if (my >= 360 && my <= 405) {
                    // Pressed Options Button
                    Game.state = Game.STATE.OPTIONS;
                }
            }
            // Quit button
            if (mx >= Game.WIDTH / 2 - 60 && mx <= Game.WIDTH / 2 + 60) {
                if (my >= 440 && my <= 495) {
                    // Pressed Quit Button
                    System.exit(1);
                }
            }

            //////// HELP MENU /////////
        } else if (Game.state == Game.STATE.HELP) {
            // Back button
            if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50) {
                if (my >= 520 && my <= 575) {
                    // Pressed Back Button
                    Game.state = Game.STATE.MENU;
                }
            }
            //////// OPTIONS MENU /////////
        } else if (Game.state == Game.STATE.OPTIONS) {
            // Back button
            if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50) {
                if (my >= 450 && my <= 505) {
//                    Pressed Mute button
                    Game.getInstance().getThemeTune().killSound();
                    Game.getInstance().setMuted(!Game.getInstance().isMuted());
                    if (!Game.getInstance().isMuted()) {
                        Game.getInstance().getThemeTune().playSound("/mainMenuTheme.wav", true);
                        OptionsMenu.muteOption = "Mute";
                    } else {
                        OptionsMenu.muteOption = "Unmute";
                    }
                }
                if (my >= 500 && my <= 555) {
                    // Pressed Back Button
                    Game.state = Game.STATE.MENU;

                } else if(mx >= Game.WIDTH / 2 - 150 && mx <= Game.WIDTH / 2 + 150){

                    if(my >= 230 && my<= 285){

                        KeyInput.keyToChange = 0;

                    } else if(my >= 310 && my<= 365){
                        KeyInput.keyToChange = 2;

                    } else if(my >= 390 && my<= 445){
                        KeyInput.keyToChange = 1;
                    }

                }

            }
            //////// DEATH MENU //////////
        } else if (Game.state == Game.STATE.DEAD || Game.state == Game.STATE.WON) {
            // Reset button
            if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50) {
                if (my >= 400 && my <= 455) {
                    // Pressed Back Button
                    Game.state = Game.STATE.GAME;
                }
            }
            // Menu button
            if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50) {
                if (my >= 480 && my <= 535) {
                    // Pressed Back Button
                    Game.state = Game.STATE.MENU;
                }
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
