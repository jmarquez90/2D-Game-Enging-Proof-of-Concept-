package com.teufel.game.entity;

import com.teufel.game.GamePanel;
import com.teufel.game.graphics.Sprite;
import com.teufel.game.states.PlayState;
import com.teufel.game.tiles.TileManager;
import com.teufel.game.util.Camera;
import com.teufel.game.util.KeyHandler;
import com.teufel.game.util.MouseHandler;
import com.teufel.game.util.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {

    private Camera cam;
    private ArrayList<Enemy> enemy;
    private TileManager tm;

    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);

        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);

        hitBounds.setWidth(42);
        hitBounds.setHeight(42);
/*
        ani.setNumFrames(4, UP);
        ani.setNumFrames(4, DOWN);
        ani.setNumFrames(4, ATTACK + RIGHT);
        ani.setNumFrames(4, ATTACK + LEFT);
        ani.setNumFrames(4, ATTACK + UP);
        ani.setNumFrames(4, ATTACK + DOWN);*/

        enemy = new ArrayList<Enemy>();
        //go = new ArrayList<GameObject>();

    }

    private void move() {
        if(up) {
            dy -= acc;
            if(dy < -maxSpeed) {
                dy = -maxSpeed;
            }

        } else {
            if(dy < 0) {
                dy += deacc;
                if(dy > 0) {
                    dy = 0;
                }
            }
        }
        if(down) {
            dy += acc;
            if(dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if(dy > 0) {
                dy -= deacc;
                if(dy < 0) {
                    dy = 0;
                }
            }
        }
        if(left) {
            dx -= acc;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }

        } else {
            if(dx < 0) {
                dx += deacc;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }
        if(right) {
            dx += acc;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }

        } else {
            if(dx > 0) {
                dx -= deacc;
                if(dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    private void resetPostion() {
        System.out.println("Resetting Player... ");
        pos.x = GamePanel.width / 2 - 32;
        PlayState.map.x = 0;

        pos.y = GamePanel.height / 2 - 32;
        PlayState.map.y = 0;

        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void update(Enemy enemy) {
        super.update();

        if(attack && hitBounds.collides(enemy.getBounds())) {
            System.out.println("I've been hit!");
        }

        if(!fallen) {
            move();
            if (!tc.collisionTile(dx, 0)) {
                PlayState.map.x += dx;
                pos.x += dx;
                xCol = false;
            } else {
                xCol = true;
            }
            if (!tc.collisionTile(0, dy)) {
                PlayState.map.y += dy;
                pos.y += dy;
                yCol = false;
            } else {
                yCol = true;
            }
        } else {
            xCol = true;
            yCol = true;
            if (ani.hasPlayedOnce()) {
                resetPostion();
                dx = 0;
                dy = 0;
                fallen = false;
            }
        }
    }


    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

        if(attack) {
            g.setColor(Color.red);
            g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getYOffset()), (int) hitBounds.getWidth(), (int) hitBounds.getHeight());
        }

        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {

        if(mouse.getButton() ==1) {
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }
        if(!fallen) {
            if (key.up.down) {
                up = true;
            } else {
                up = false;
            }
            if (key.down.down) {
                down = true;
            } else {
                down = false;
            }
            if (key.left.down) {
                left = true;
            } else {
                left = false;
            }
            if (key.right.down) {
                right = true;
            } else {
                right = false;
            }

            if (key.attack.down) {
                attack = true;
            } else {
                attack = false;
            }
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
    }

}
