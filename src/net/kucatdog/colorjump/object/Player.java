package net.kucatdog.colorjump.object;

import net.kucatdog.colorjump.gui.GamePanel;

import java.awt.*;

/**
 * Created by junha on 2017. 8. 17..
 */
public class Player extends Sprite {

    public static final int RADIUS = 20;

    public static final double H_SPEED = 300;
    public static final double JUMP_SPEED = 500;

    public double speed = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void jump() {
        speed = JUMP_SPEED;
    }

    @Override
    public void draw(Graphics g, int cameraY) {
        g.setColor(color != null ? color : Color.lightGray);
        g.fillOval(x - RADIUS, GamePanel.HEIGHT - y + cameraY - RADIUS * 2, RADIUS * 2, RADIUS * 2);
    }
}
