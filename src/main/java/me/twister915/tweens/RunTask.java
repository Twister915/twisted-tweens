package me.twister915.tweens;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public final class RunTask implements Interpolatable {
    private final Runnable target;
    @Getter @Setter private boolean run, started;

    public static RunTask call(Runnable runnable) {
        return new RunTask(runnable);
    }

    private RunTask(Runnable target) {
        this.target = target;
    }

    public void interpolate(BigDecimal factor) throws ReflectiveOperationException {
        if (!started) throw new IllegalStateException("This tween has not started!");
        target.run();
    }

    public long getLength() {
        return 1;
    }

    public long getPassedTime() {
        return run ? 1 : 0;
    }

    public void timePassed(long time) {
        if (!run && started) run = true;
    }

    public void resetTimePassed() {
        run = false;
    }

    public boolean canFinish() {
        return true;
    }
}
