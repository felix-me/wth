package com.JHEF.wth.framework;

import com.JHEF.wth.window.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    private Clip clip;

    public void playSound(String file, boolean loop) {

        if (Game.getInstance().isNotMuted()) {
            try {
                String currentDir = System.getProperty("user.dir") + "/res";
                File f = new File(currentDir + file);
                AudioInputStream ais = AudioSystem.getAudioInputStream(f);
                clip = AudioSystem.getClip();
                clip.open(ais);
                if (loop) {
                    clip.loop(1000);
                } else {
                    clip.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void killSound() {
        if(clip != null) {
            clip.stop();
        }
    }

}
