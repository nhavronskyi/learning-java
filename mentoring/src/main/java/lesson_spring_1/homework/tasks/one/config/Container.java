package lesson_spring_1.homework.tasks.one.config;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private final Map<Class<?>, Object> bindings = new HashMap<>();

    public void bind(Class<?> cls, Object instance) {
        bindings.put(cls, instance);
    }

    public <T> void bind(Class<T> cls) {
        try {
            bindings.put(cls, createInstance(cls));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance for " + cls.getName(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T make(Class<T> cls) {
        if (!bindings.containsKey(cls)) {
            throw new RuntimeException("No binding found for " + cls.getName());
        }
        Object binding = bindings.get(cls);
        return binding instanceof Class<?> ? createInstance((Class<T>) binding)
                : cls.cast(binding);
    }

    @SuppressWarnings("unchecked")
    private <T> T createInstance(Class<T> cls) {
        try {
            Constructor<?>[] constructors = cls.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = Arrays.stream(parameterTypes)
                        .map(this::make)
                        .toArray();
                return (T) constructor.newInstance(parameters);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance for " + cls.getName(), e);
        }
        throw new RuntimeException("No suitable constructor found for " + cls.getName());
    }
}
