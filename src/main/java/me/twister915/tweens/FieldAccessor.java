package me.twister915.tweens;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class FieldAccessor<T> extends Accessor<T> {
    private final Field field;
    private final Object target;

    public FieldAccessor(String targetKey, Object target) throws IllegalAccessException {
        super(targetKey, target);
        FieldData fieldData = resolveField(target.getClass(), target, targetKey);
        field = fieldData.field;
        this.target = fieldData.object;
        try {
            bootstrapField(field);
        } catch (Exception e) {
            throw new IllegalArgumentException("The field you specified is invalid!", e);
        }
        if (Modifier.isFinal(field.getModifiers())) throw new IllegalArgumentException("The field is final. Please do not final fields you wish to interpolate!");
    }

    static FieldData resolveField(Class<?> objectClass, Object target, String field) throws IllegalAccessException {
        int i = field.indexOf('.');
        if (i != -1) {
            String pre = field.substring(0, i), post = field.substring(i+1);
            FieldData myFieldData = resolveField(objectClass, target, pre);
            Field myField = myFieldData.field;
            try {
                bootstrapField(myField);
            } catch (ReflectiveOperationException e) {
                throw new IllegalArgumentException("One of the fields (" + pre + ") you specified is invalid!", e);
            }
            Object newTarget = myField.get(target);
            return resolveField(newTarget.getClass(), newTarget, post);
        }
        try {
            return new FieldData(objectClass.getDeclaredField(field), target);
        } catch (NoSuchFieldException e) {
            return objectClass == Object.class ? null : resolveField(objectClass.getSuperclass(), target, field);
        }
    }

    static void bootstrapField(Field field) throws ReflectiveOperationException {
        if (field == null) throw new NullPointerException("field");
        field.setAccessible(true);
    }

    static class FieldData {
        private final Field field;
        private final Object object;

        FieldData(Field field, Object object) {
            this.field = field;
            this.object = object;
        }
    }

    @Override
    T getCurrentValue(Object target, String loc) {
        try {
            //noinspection unchecked
            return (T) field.get(this.target);
        } catch (Exception ignored) {}
        return null;
    }

    @Override
    Class<T> getType(Object target, String loc) {
        return (Class<T>) field.getType();
    }

    @Override
    void set(Object target, String loc, T value) {
        try {
            field.set(this.target, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not set value!");
        }
    }
}
