package org.game.common;

public interface EnumMsg<T> {
    T getValue();

    String getName();

    default boolean isEmpty() {
        return false;
    }
}
