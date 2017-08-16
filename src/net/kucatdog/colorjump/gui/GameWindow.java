package net.kucatdog.colorjump.gui;

import javax.swing.*;

/**
 * Created by junha on 2017. 8. 17..
 */
public class GameWindow extends JFrame {

    private GamePanel gamePanel;

    GameWindow() {
        gamePanel = new GamePanel();
        add(gamePanel);
        addKeyListener(gamePanel);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
    }

    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.setVisible(true);
    }
}
