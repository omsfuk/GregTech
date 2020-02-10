package gregtech.api.capability.tool;

public class IdleTracker {

    private int idle = 1;
    private int minIdle;
    private int maxIdle;
    private int step;
    private long timer = 0;

    public IdleTracker() {
        this(1, 60, 1);
    }

    public IdleTracker(int minIdle, int maxIdle) {
        this(minIdle, maxIdle, 1);
    }

    public IdleTracker(int minIdle, int maxIdle, int step) {
        this.minIdle = minIdle;
        this.maxIdle = maxIdle;
        this.step = step;
    }

    /**
     * provide external timer
     * @param tick
     * @return
     */
    public boolean canAction(long tick) {
        if (tick % idle == 0) {
            return true;
        }
        return false;
    }

    /**
     * use inner timer
     * @return
     */
    public boolean canAction() {
        if (timer % idle == 0) {
            return true;
        }
        return false;
    }

    public int reset() {
        return idle = minIdle;
    }

    public long update() {
        return ++timer;
    }

    public long getTimer() {
        return timer;
    }

    public int inc() {
        return idle = (idle + step < maxIdle ? maxIdle : idle + step);
    }

    public int dec() {
        return idle = (idle - step < minIdle ? minIdle : idle - step);
    }

    public int getIdle() {
        return idle;
    }
}
