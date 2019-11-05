package com.teufel.game;

import javax.swing.JFrame;
import java.awt.event.ComponentListener;

public class Window extends JFrame {

    public Window() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280, 720));
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
