package me.twister915.tweens;

import java.math.BigDecimal;

/**
 * Specifies how type {@link T} should interpolate. This should be in linear terms. The factor specified in the sole method ({@link #interpolate(Object, Object, BigDecimal)}) will be provided by another function, taking into account smooth animation (EASE functions). Treat the value exactly as specified by the method documentation, as an exact distance factor from start towards finish.
 * @param <T> The type that this TweenAccessor lerps linearly.
 */
public interface Interpolator<T> {
    /**
     * Return a modified value that represents the value between the start and the target using the factor as the position on that line.
     * @param start The start value, for reference
     * @param target The target value, for reference
     * @param factor The distance from the start towards the target
     * @return The current value at this position specified by {@code factor}
     */
    T interpolate(T start, T target, BigDecimal factor);
}
