package me.twister915.tweens;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of default type accessors. This allows floats, among other default types, to automatically be used by {@link Tween}
 *
 * You may also submit default {@link Interpolator} instances for types.
 */
@SuppressWarnings("unchecked")
public final class InterpolatorRegistry {
    private static Map<Type, Interpolator<?>> typeAccessors = new HashMap<Type, Interpolator<?>>();

    static {
        //default number interpolator
        registerTypeAccessor(new NumberInterpolator<Float>() {
            @Override
            Float from(BigDecimal f) {
                return f.floatValue();
            }
        }, Float.class, float.class);

        registerTypeAccessor(new NumberInterpolator<Double>() {
            @Override
            Double from(BigDecimal f) {
                return f.doubleValue();
            }
        }, Double.class, double.class);

        registerTypeAccessor(new NumberInterpolator<Integer>() {
            @Override
            Integer from(BigDecimal f) {
                return f.intValue();
            }
        }, Integer.class, int.class);

        registerTypeAccessor(new NumberInterpolator<Long>() {
            @Override
            Long from(BigDecimal f) {
                return f.longValue();
            }
        }, Long.class, long.class);

        registerTypeAccessor(new NumberInterpolator<Short>() {
            @Override
            Short from(BigDecimal f) {
                return f.shortValueExact();
            }
        }, Short.class, short.class);
    }

    static <T> Interpolator<?> getInterpolator(Class<T> type) {
        return typeAccessors.get(type);
    }

    /**
     * Allows you to register your {@link Interpolator} for a type.
     * @param types The type that you're registering this tween interpolator for.
     * @param accessor The interpolator you have created
     * @param <T> The type specified by the type paramater above. The {@code T} of the interpolator (first generic param) must be the same as the Class specified in the first param of this method.
     */
    public static <T> void registerTypeAccessor(Interpolator<T> accessor, Class<T>... types) {
        for (Class<T> type : types) typeAccessors.put(type, accessor);
    }

    private static abstract class NumberInterpolator<T extends Number> implements Interpolator<T> {
        abstract T from(BigDecimal f);

        public T interpolate(T start, T target, BigDecimal factor) {
            BigDecimal startBig = BigDecimal.valueOf(start.floatValue()), targetBig = BigDecimal.valueOf(target.floatValue());
            return from(startBig.add(targetBig.subtract(startBig).multiply(factor))); //start + ((target - start) * factor)
        }
    }
}
