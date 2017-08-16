package net.kucatdog.colorjump.object;

import net.kucatdog.colorjump.gui.GamePanel;

import java.awt.*;

/**
 * Created by junha on 2017. 8. 17..
 */
public class Block extends Sprite {

    public static final int HEIGHT = 20;
    public static final int WIDTH = 100;

    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean isCollapse(int x, int y) {
        return (x >= this.x - WIDTH / 2 &&
                x <= this.x + WIDTH / 2 &&
                y >= this.y - HEIGHT / 2 &&
                y <= this.y + HEIGHT / 2);
    }

    @Override
    public void draw(Graphics g, int cameraY) {
        g.setColor(color);
        g.fillRect(
            x - WIDTH / 2,
            GamePanel.HEIGHT - y + cameraY - HEIGHT / 2,
            WIDTH,
            HEIGHT
        );
    }
}
