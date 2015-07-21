package me.twister915.tweens;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data public abstract class BaseInterpolateable implements Interpolatable {
    private long length;
    private long passedTime;
    private boolean started;
    @Setter(AccessLevel.PROTECTED) private boolean currentDirection = true, yoyo, loop;

    public BaseInterpolateable() {}
    public BaseInterpolateable(long ms) {length = ms;}

    public void timePassed(long time) {
        passedTime = (long) range(0, passedTime + (currentDirection ? 1 : -1) * time, length);
        if (!inRange(0, passedTime, length, false)) {
            if (loop) passedTime = 0;
            else if (yoyo) currentDirection = !currentDirection;
        }
    }

    public boolean canFinish() {
        return !yoyo && !loop;
    }

    public void resetTimePassed() {
        currentDirection = true;
        passedTime = 0;
    }

    public static double range(double min, double x, double max) {
        return x < min ? min : x > max ? max : x;
    }


    public static boolean inRange(double min, double x, double max, boolean inclusive) {
        return !(inclusive ? x < min || x > max : x <= min || x >= max);
    }
}
