package net.kucatdog.colorjump.util;

import net.kucatdog.colorjump.gui.GamePanel;
import net.kucatdog.colorjump.object.Block;

import java.awt.*;
import java.util.Random;

/**
 * Created by junha on 2017. 8. 17..
 */
public class BlockGenerator {

    public static final int MIN_SPACING = 40;
    public static final int MAX_SPACING = 160;

    private Random random = new Random();

    public Block generate(Block lastBlock) {
        int lastY = lastBlock != null ? lastBlock.y : 0;

        int x = random.nextInt(GamePanel.WIDTH);
        int y = lastY + MIN_SPACING + random.nextInt(MAX_SPACING - MIN_SPACING);

        Color color;
        switch(random.nextInt(3)) {
            case 0:
                color = Color.red;
                break;
            case 1:
                color = Color.green;
                break;
            default:
                color = Color.blue;
                break;
        }

        return new Block(x, y, color);
    }
}
