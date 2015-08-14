package me.twister915.tweens;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * A manager for a set of {@link Interpolatable} objects submitted to it. Think of it as a pool of Tweens running in parallel.
 *
 * Simply create an instance, and call the {@link #update(long, TimeUnit)} method in your render/update method with a time interval, and the tweens will interpolate.
 */
@Data
public final class TweenManager {
    @Getter(AccessLevel.NONE) private final List<Interpolatable> activeInterpolatables = new ArrayList<Interpolatable>();
    private boolean paused = false;

    /**
     * Will interpolate all {@link Interpolatable} objects that this {@link TweenManager} manages.
     * @param time The amount of time in the units specified by the second argument.
     * @param unit The unit of time the amount is specified in. Accuracy past 1ms will be lost (no fractions of a millisecond)
     */
    public void update(long time, TimeUnit unit) {
        if (paused || activeInterpolatables.size() == 0) return;
        long ms = TimeUnit.MILLISECONDS.convert(time, unit);
        Iterator<Interpolatable> iterator = activeInterpolatables.iterator();
        while (iterator.hasNext()) {
            Interpolatable activeTween = iterator.next();
            long length = activeTween.getLength(), passedTime = Math.min(length, activeTween.getPassedTime() + ms);
            activeTween.timePassed(ms);
            try {
                activeTween.interpolate(BigDecimal.valueOf(passedTime).divide(BigDecimal.valueOf(length), 4, BigDecimal.ROUND_CEILING));
            } catch (ReflectiveOperationException e) {
                continue;
            }
            if (length == passedTime && activeTween.canFinish()) {
                iterator.remove();
                activeTween.setStarted(false);
            }
        }
    }

    /**
     * Submits an {@link Interpolatable} to the manager to be run. This will call {@link Interpolatable#resetTimePassed()} and {@link Interpolatable#setStarted(boolean)} with the argument {@code true}, before finally adding the {@link Interpolatable} to the pool.
     * @param interpolatable The {@link Interpolatable} to begin.
     */
    public void start(Interpolatable interpolatable) {
        interpolatable.setStarted(true);
        interpolatable.resetTimePassed();
        activeInterpolatables.add(interpolatable);
    }

    /**
     * Will cause the manager to perform no action when {@link #update(long, TimeUnit)} is called until {@link #unpause()} is called.
     */
    public void pause() {
        paused = true;
    }

    /**
     * Will cause the manager to restore normal functionality, and {@link #update(long, TimeUnit)} will resume interpolation on all {@link Interpolatable}s.
     */
    public void unpause() {
        paused = false;
    }

    /**
     * Will remove all {@link Interpolatable} objects from the manager's control and set them as not started.
     */
    public void killAll() {
        for (Interpolatable activeTween : activeInterpolatables) activeTween.setStarted(false);
        activeInterpolatables.clear();
    }

    public void kill(Interpolatable interpolatable) {
        interpolatable.setStarted(false);
        activeInterpolatables.remove(interpolatable);
    }
}
