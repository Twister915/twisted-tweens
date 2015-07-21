package me.twister915.tweens;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Represents something that can be managed by the {@link TweenManager} through interpolation.
 */
public interface Interpolatable {
    /**
     * Should interpolate based on the factor.
     * @param factor A percentage value in range of [0,1] which is the percentage distance between start and end of this tween. Easing must be dealt with by the implementor.
     * @throws ReflectiveOperationException An exception with reflective operations, used by the internal {@link Tween} class.
     */
    void interpolate(BigDecimal factor) throws ReflectiveOperationException;

    /**
     * The number of milliseconds this tween is slated to take. This should not change.
     * @return As specified in method description.
     */
    long getLength();

    /**
     * Gets the amount of time that has so far passed. This is expected to not be augmented by any easing functions.
     * @return The amount of time that has so far passed.
     */
    long getPassedTime();

    /**
     * Called by the manager to notify the {@link Interpolatable} that there has been a change in time. Add this onto anything used to track the amount of time passed and return the sum of all values of {@link #timePassed(long)} calls in {@link #getPassedTime()}
     * @param time The amount of time that has passed
     */
    void timePassed(long time);

    /**
     * Set time passed to 0 if tracking. This is called when the {@link Interpolatable} is removed.
     */
    void resetTimePassed();

    /**
     * Sets the started value for this {@link Interpolatable}. Please return this value in {@link #isStarted()}
     * @param started The value to set.
     */
    void setStarted(boolean started);

    /**
     * Return whether this {@link Interpolatable} has been started by the manager. Set by {@link #setStarted(boolean)}.
     * @return The current value of <em>started</em>
     */
    boolean isStarted();

    /**
     * When the {@link #getPassedTime()} after the {@link #timePassed(long)} call (as computed in {@link TweenManager#update(long, TimeUnit)} method) is equal to or greater than {@link #getLength()}, this method will be called. If the return value of this method is true, the {@link Interpolatable} will be removed from the manager's control and cleaned up. Otherwise, it will remain in the manager's control and no action will occur. Please take care to reset your own states as you please if you choose to return true for this method.
     * @return If the {@link Interpolatable} should be completed and cleaned by the manager at the end of the interpolation.
     */
    boolean canFinish();
}
