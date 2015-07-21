package me.twister915.tweens;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.twister915.tweens.equation.Quad;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data @Setter(AccessLevel.PACKAGE) public final class Tween<T> extends BaseInterpolateable {
    private final Object target;
    private final Accessor<T> accessor;
    private final String targetKey;
    private T start;
    private final T end;
    private final Class<T> type;
    private Interpolator<T> interpolator;
    private EasingFunction easingFunction = Quad.INOUT;

    /**
     * Creates a tween that modifies a key over a period of time.
     * @param target The object that has the key we wish to modify.
     * @param key The key that we wish to modify.
     * @param to The destination value. The current value will be used as a starting point.
     * @param length The amount of time (in seconds) that this tween should take. This is converted to an absolute number of milliseconds, thus this must be larger than 0.001 seconds.
     * @return A tween object that allows you to continue configuring the tween.
     */
    public static Tween on(Object target, String key, Object to, float length) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null!");
        if (to == null) throw new IllegalArgumentException("To cannot be null!");
        if (key == null) throw new IllegalArgumentException("Key cannot be null!");
        Accessor<?> aFor = AccessorRegistry.getFor(target, key);
        if (aFor == null) throw new IllegalArgumentException("Could not find an accessor!");
        Class<?> type = aFor.getType(target, key);
        //noinspection unchecked
        return new Tween(target, key, aFor, to, length, InterpolatorRegistry.getInterpolator(type), type);
    }

    private Tween(Object target, String key, Accessor<T> accessor, T end, float length, Interpolator<T> interpolator, Class<T> type) {
        super((long) (length * 1000));
        this.type = type;
        if (interpolator == null) throw new IllegalArgumentException("No interpolator specified!");
        this.target = target;
        this.end = end;
        this.interpolator = interpolator;
        this.accessor = accessor;
        this.targetKey = key;
    }

    public void interpolate(BigDecimal factor) throws IllegalAccessException {
        if (!isStarted()) throw new IllegalStateException("You cannot interpolate a tween that has not been started!");
        if (start == null) start = accessor.getCurrentValue(target, targetKey);
        accessor.set(target, targetKey, interpolator.interpolate(start, end, easingFunction.getFactor(factor)));
    }

    public void start(TweenManager manager) {
        if (isStarted()) throw new IllegalStateException("You cannot start a tween that is already started!");
        manager.start(this);
    }

    public Tween<T> withEasingFunction(EasingFunction easingFunction) {
        setEasingFunction(easingFunction);
        return this;
    }

    public Tween<T> withTweenAccessor(Interpolator<T> accessor) {
        setInterpolator(accessor);
        return this;
    }

    public Tween<T> yoyo() {
        setYoyo(!isYoyo());
        return this;
    }

    public Tween<T> loop() {
        setLoop(!isLoop());
        return this;
    }

    @Override
    public void resetTimePassed() {
        super.resetTimePassed();
        start = null;
    }
}
