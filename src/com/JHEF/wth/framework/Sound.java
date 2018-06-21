package com.JHEF.wth.framework;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    File f;
    AudioInputStream ais;
    Clip clip;

        public void playSound(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

            f = new File(file);
            ais = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();

        }

        public void killSound() {
            clip.stop();
        }





    }
