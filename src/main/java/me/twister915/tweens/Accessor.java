package me.twister915.tweens;

public abstract class Accessor<T> {
    private final String targetKey;
    private final Object target;

    protected Accessor(String targetKey, Object target) {
        this.targetKey = targetKey;
        this.target = target;
    }

    abstract void set(Object target, String loc, T value);
    abstract T getCurrentValue(Object target, String loc);
    abstract Class<T> getType(Object target, String loc);
}
