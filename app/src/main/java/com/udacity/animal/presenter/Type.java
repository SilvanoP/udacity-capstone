package com.udacity.animal.presenter;

public enum Type {

    ANIME("anime"),
    MANGA("manga");

    private String value;

    Type(String value) {
        this.value = value;
    }

    public static Type getType(String name) {
        if (name.equals(ANIME.getValue())) {
            return ANIME;
        } else if (name.equals(MANGA.getValue())) {
            return MANGA;
        }

        return null;
    }

    public String getValue() {
        return value;
    }
}
