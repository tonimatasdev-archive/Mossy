package net.tonimatasdev.mossy.utils.collection;

public sealed interface ObjectArray<T>
        permits ObjectArrayImpl.SingleThread, ObjectArrayImpl.Concurrent {
    static <T> ObjectArray<T> singleThread(int initialSize) {
        return new ObjectArrayImpl.SingleThread<>(initialSize);
    }

    static <T> ObjectArray<T> singleThread() {
        return singleThread(0);
    }

    static <T> ObjectArray<T> concurrent(int initialSize) {
        return new ObjectArrayImpl.Concurrent<>(initialSize);
    }

    static <T> ObjectArray<T> concurrent() {
        return concurrent(0);
    }

    T get(int index);

    void set(int index, T object);

    default void remove(int index) {
        set(index, null);
    }

    void trim();

    T [] arrayCopy(Class<T> type);
}
