package com.example.api.exception;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public final class Ensure {


    private Ensure() {
        throw new AssertionError("No \"Ensure\" instances for you!");
    }

    public static <T> T requireNonNull(T obj, final Supplier<? extends Exception> exception) throws Exception {
        if (obj == null)
            throw exception.get();
        return obj;
    }

    public static String requireNonEmpty(final CharSequence cs, final Supplier<? extends Exception> exception) throws Exception {
        if (cs == null || cs.length() == 0)
            throw exception.get();
        return (String) cs;
    }

    public static <T> Collection<T> requireNonEmpty(final Collection<T> collection, final Supplier<? extends Exception> exception) throws Exception {
        if (collection == null || collection.isEmpty())
            throw exception.get();
        return collection;
    }

    public static <K, V> Map<K, V> requireNonEmpty(final Map<K, V> map, final Supplier<? extends Exception> exception) throws Exception {
        if (map == null || map.isEmpty())
            throw exception.get();
        return map;
    }

    public static void ifThenThrow(final Boolean ifThen, final Supplier<? extends Exception> exception) throws Exception {
        if (ifThen)
            throw exception.get();
    }


}
