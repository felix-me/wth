package com.JHEF.wth.framework;

import com.JHEF.wth.window.Game;

public class SoundCaller {

    public static void playGameThemes() {
        Sound sound = new Sound();

        try {
            if (Game.state == Game.STATE.MENU) {
                sound.playSound("C:\\Users\\User\\IdeaProjects\\wth\\res\\mainMenuTheme.wav");
            } else {
                sound.killSound();
                System.out.println("Sound killed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
