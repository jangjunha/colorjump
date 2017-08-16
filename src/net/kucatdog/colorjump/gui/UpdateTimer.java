package net.kucatdog.colorjump.gui;

/**
 * Created by junha on 2017. 8. 17..
 */
public class UpdateTimer extends Thread {

    public static final int DELAY = 15;

    private boolean stop = false;
    private UpdateListener listener;

    public UpdateTimer(UpdateListener listener) {
        this.listener = listener;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long currentTime;
        while (!stop) {
            currentTime = System.nanoTime();
            listener.update((currentTime - lastTime) / 1_000_000_000.0);
            lastTime = currentTime;

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
