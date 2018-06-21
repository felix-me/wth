package com.JHEF.wth.framework;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    File f;
    AudioInputStream ais;
    Clip clip;
    String currentDir;

        public void playSound(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

            if (!Game.getInstance().isMuted()) {
               currentDir = System.getProperty("user.dir")+"/res";
               f = new File(currentDir+file);
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
