package com.teufel.game.states;

import com.teufel.game.GamePanel;
import com.teufel.game.entity.Enemy;
import com.teufel.game.entity.Player;
import com.teufel.game.graphics.Font;
import com.teufel.game.graphics.Sprite;
import com.teufel.game.tiles.TileManager;
import com.teufel.game.util.*;

import java.awt.Graphics2D;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private Enemy enemy;
    private TileManager tm;
    private Camera cam;
    public static Vector2f map;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        cam = new Camera(new AABB(new Vector2f(GamePanel.width / 2 - 800 / 2, GamePanel.height / 2 - 600 / 2), 800, 600));

        tm = new TileManager("Tiles/tilemap.xml", cam);
        font = new Font ("font/galaxyfont.png", 20, 20);

        enemy = new Enemy(new Sprite("Entity/littlegirl.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + 150, 0 + (GamePanel.height /2) - 32 + 150), 64);
        player = new Player(new Sprite("Entity/link.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height /2) - 32), 64);
        cam.target(player);
    }

    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update(enemy);
        enemy.update(player);
        cam.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
        cam.input(mouse, key);
    }

    public void render (Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 192, 32), 32, 32);
        player.render(g);
        enemy.render(g);
        cam.render(g);
    }

}
