package me.twister915.tweens;

public interface AccessorProvider {
    Accessor<?> getFor(Object target, String key);
    boolean appliesTo(Object target, String key);
}
