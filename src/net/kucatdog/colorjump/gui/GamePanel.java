package net.kucatdog.colorjump.gui;

import net.kucatdog.colorjump.object.Block;
import net.kucatdog.colorjump.object.Player;
import net.kucatdog.colorjump.util.BlockGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by junha on 2017. 8. 17..
 */
public class GamePanel extends JPanel implements UpdateListener, KeyListener {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 800;
    public static final double GRAVITATIONAL_ACCELERATION = 600;

    private UpdateTimer timer;
    private BlockGenerator blockGenerator = new BlockGenerator();
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    private boolean isPlaying;
    private Player player;
    private List<Block> blocks;
    private int cameraY;

    GamePanel() {
        super(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        start();
    }

    private void start() {
        isPlaying = true;
        cameraY = 0;

        // 블럭 초기화
        blocks = new ArrayList<>();

        // 플레이어 생성
        player = new Player(200, 760);

        // 타이머 시작
        timer = new UpdateTimer(this);
        timer.start();
    }

    private void end() {
        timer.setStop(true);

        isPlaying = false;
    }

    private void restart() {
        end();
        start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Block block : blocks) {
            block.draw(g, cameraY);
        }

        player.draw(g, cameraY);

        g.setColor(Color.black);
        g.drawString("Score: " + cameraY, 10, 20);

        if (!isPlaying) {
            g.drawString("Game Over!", 160, 200);
        }
    }

    @Override
    public void update(double delta) {
        // 플레이어 이동 - x
        if (isLeftPressed) player.x -= Player.H_SPEED * delta;
        if (isRightPressed) player.x += Player.H_SPEED * delta;
        player.x = (player.x + GamePanel.WIDTH) % GamePanel.WIDTH;

        // 플레이어 이동 - y
        player.y += player.speed * delta;
        player.speed -= GRAVITATIONAL_ACCELERATION * delta;

        // 카메라 이동
        if (player.speed > 0) {
            cameraY = Math.max(cameraY, player.y - HEIGHT / 3 * 2);
        }

        // 게임 종료 체크
        if (player.y < cameraY - Player.RADIUS * 2) {
            end();
        }

        // 충돌 처리
        for (Block block : blocks) {
            if (player.speed < 0 && player.color == block.color && block.isCollapse(player.x, player.y)) {
                player.jump();
            }
        }

        // 블럭 생성
        Block lastBlock = blocks.isEmpty() ? null : blocks.get(blocks.size() - 1);
        while (lastBlock == null || lastBlock.y <= cameraY + HEIGHT) {
            Block block = blockGenerator.generate(lastBlock);
            blocks.add(block);

            lastBlock = block;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.color = Color.red;
                break;
            case KeyEvent.VK_S:
                player.color = Color.green;
                break;
            case KeyEvent.VK_D:
                player.color = Color.blue;
                break;
            case KeyEvent.VK_LEFT:
                isLeftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                isRightPressed = true;
                break;
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R) {
            restart();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                isLeftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRightPressed = false;
                break;
        }
    }
}
