package com.teufel.game.tiles.block;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.teufel.game.util.Vector2f;
import com.teufel.game.util.AABB;

public class NormBlock extends Block {

    public NormBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    public boolean update(AABB p) {
        return false;
    }
    public boolean isInside(AABB p) {
        return false;
    };

    public void render(Graphics2D g) {
        super.render(g);
    }
}
