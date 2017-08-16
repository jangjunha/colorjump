package net.kucatdog.colorjump.object;

import java.awt.*;

/**
 * Created by junha on 2017. 8. 17..
 */
public abstract class Sprite {

    public int x, y;
    public Color color;

    abstract void draw(Graphics g, int cameraY);
}
