package com.JHEF.wth.framework;

import com.JHEF.wth.window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {
  
    SpriteSheet bs, ps, is, pus, gw;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage imp_sheet = null;

    private BufferedImage powerup_sheet = null;
    private BufferedImage wings_sheet = null;

    public BufferedImage[] block = new BufferedImage[24];
    public BufferedImage[] player = new BufferedImage[11];
    public BufferedImage[] imp = new BufferedImage[50];
    public BufferedImage[] playerJump = new BufferedImage[2];
    public BufferedImage[] playerWings = new BufferedImage[6];

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            block_sheet = loader.loadImage("/WTH_TEXTURES.png");
            player_sheet = loader.loadImage("/ghosts.png");
            imp_sheet = loader.loadImage("/imp.png");
            powerup_sheet = loader.loadImage("/powerup.png");
            wings_sheet = loader.loadImage("/ghost_wings.png");
        } catch(Exception e) {
            e.printStackTrace();
        }

        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        is = new SpriteSheet(imp_sheet);
        pus = new SpriteSheet(powerup_sheet);
        gw = new SpriteSheet(wings_sheet);

        getTextures();

    }

    private void getTextures() {
        block[0] = bs.grabImage(1,3,32,32); //dirt
        block[1] = bs.grabImage(2,3,32,32); //top grass
        block[2] = bs.grabImage(3,3,32,32); //top left grass
        block[3] = bs.grabImage(4,3,32,32); //top right grass
        block[4] = bs.grabImage(5,3,32,32); //left grass
        block[5] = bs.grabImage(6,3,32,32); //right water
        block[6] = bs.grabImage(7,3,32,32); //top water
        block[7] = bs.grabImage(8,3,32,32); //bottom water
        block[8] = bs.grabImage(7,2,32,32); //top lava
        block[9] = bs.grabImage(8,2,32,32); //bottom lava
        block[10] = bs.grabImage(2,2,32,32); //rock top
        block[11] = bs.grabImage(3,2,32,32); //rock top lfet
        block[12] = bs.grabImage(4,2,32,32); //rock top right
        block[13] = bs.grabImage(1,2,32,32); //rock
        block[14] = bs.grabImage(6,2,32,32); //right rock
        block[15] = bs.grabImage(5,2,32,32); //left rock
        block[16] = bs.grabImage(3,1,32,32); //top left cloud
        block[17] = bs.grabImage(2,1,32,32); //top middle cloud
        block[18] = bs.grabImage(4,1,32,32); //top right cloud
        block[19] = bs.grabImage(7,1,32,32); //bottom left cloud
        block[20] = bs.grabImage(8,1,32,32); //bottom middle cloud
        block[21] = bs.grabImage(9,1,32,32); //bottom right cloud
        block[22] = pus.grabImage(1,1,32,32);
        block[23] = pus.grabImage(2,1,32,32);

        player[0] = ps.grabImage(1,2,32,64);//player facing right
        player[1] = ps.grabImage(2,2,32,64);//player facing right 2
        player[2] = ps.grabImage(1,4,32,64);//player damage right
        player[3] = ps.grabImage(1,5,32,64);//player damage right 2

        player[4] = ps.grabImage(1,1,32,64);//player facing left
        player[5] = ps.grabImage(2,1,32,64);//player facing left left 2
        player[6] = ps.grabImage(1,3,32,64);//player damage left
        player[7] = ps.grabImage(2,3,32,64);//player damage left 2

        playerJump[0] = ps.grabImage(1,5,32,64);//player jump right
        playerJump[1] = ps.grabImage(2,5,32,64);//player jump left

        playerWings[0] = gw.grabImage(1,1,32,64);//wings right 1
        playerWings[1] = gw.grabImage(2,1,32,64);//wings right 2
        playerWings[2] = gw.grabImage(3,1,32,64);//wings right 3

        playerWings[3] = gw.grabImage(1,2,32,64);//wings right 1
        playerWings[4] = gw.grabImage(2,2,32,64);//wings right 2
        playerWings[5] = gw.grabImage(3,2,32,64);//wings right 3


        imp[0] = is.grabImage(2,1,32,32);//player jump left


    }
}
