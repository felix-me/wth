package com.JHEF.wth.framework;

import com.JHEF.wth.window.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    File f;
    AudioInputStream ais;
    Clip clip;

        public void playSound(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

            if (!Game.getInstance().isMuted()) {
                f = new File(file);
                ais = AudioSystem.getAudioInputStream(f);
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.loop(1000);
            }

        }

        public void killSound() {
            clip.stop();
        }

}
