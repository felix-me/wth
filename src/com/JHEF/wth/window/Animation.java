package com.JHEF.wth.window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed;

    private int frames;

    private int count = 0;

    private int index = 0;

    private BufferedImage[] images;

    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage... args){
        this.speed = speed;
        images = new BufferedImage[args.length];
        System.arraycopy(args, 0, images, 0, args.length);
        frames = args.length;


    }

    public void runAnimation(){

        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame(){
        for(int i = 0; i < frames; i++){
            if(count == i) currentImg = images[i];

        }

        count++;
        if(count > frames) count = 0;
    }

    public void drawAnimation(Graphics g, int x, int y){

        g.drawImage(currentImg,x,y,null);
    }

}


