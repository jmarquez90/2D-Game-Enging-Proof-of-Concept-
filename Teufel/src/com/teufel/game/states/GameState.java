package com.teufel.game.states;

import com.teufel.game.util.KeyHandler;
import com.teufel.game.util.MouseHandler;

import java.awt.*;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
