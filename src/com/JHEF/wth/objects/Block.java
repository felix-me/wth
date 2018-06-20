package com.JHEF.wth.objects;

import com.JHEF.wth.framework.GameObject;
import com.JHEF.wth.framework.ObjectId;
import com.JHEF.wth.framework.Texture;
import com.JHEF.wth.window.Animation;
import com.JHEF.wth.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {

    Texture tex = Game.getTexture();
    private int type;
    private Animation wingFlap;

    /**
     * constructor for {@link GameObject}
     *
     * @param x pos x
     * @param y pos y
     * @param id object id
     */
    public Block(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
        if(type == 22) {
            wingFlap = new Animation(10, tex.block[22], tex.block[23]);
        }
    }

    public void tick(LinkedList<GameObject> object) {
        if(type == 22) {
            wingFlap.runAnimation();
        }
    }

    public void render(Graphics g) {
        if(type == 22) {
            wingFlap.drawAnimation(g,(int)x,(int)y);
        } else {
            g.drawImage(tex.block[type], (int) x, (int) y, null);
        }
    }

    public int getType() {
        return type;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }


}
