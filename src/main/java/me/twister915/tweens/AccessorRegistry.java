package me.twister915.tweens;

import java.util.ArrayList;
import java.util.List;

public final class AccessorRegistry {
    private static final List<AccessorProvider> providers = new ArrayList<AccessorProvider>();

    public static Accessor<?> getFor(Object target, String key) {
        for (AccessorProvider provider : providers)
            if (provider.appliesTo(target, key)) return provider.getFor(target, key);
        return null;
    }

    public static void register(AccessorProvider provider) {
        providers.add(provider);
    }

    static {
        register(new AccessorProvider() {
            public Accessor<?> getFor(Object target, String key) {
                try {
                    return new FieldAccessor<Object>(key, target);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Could not access that field!");
                }
            }

            public boolean appliesTo(Object target, String key) {
                try {
                    return FieldAccessor.resolveField(target.getClass(), target, key) != null;
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }
}
