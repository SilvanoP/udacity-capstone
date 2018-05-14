package com.udacity.animal.presenter;

import android.util.SparseArray;

public enum Status {

    WATCHING_READING(1),
    COMPLETED(2),
    ON_HOLD(3),
    DROPPED(4),
    PLAN_TO_WATCH_READ(6);

    private static SparseArray<Status> map = new SparseArray<>();
    private int value;

    Status(int value) {
        this.value = value;
    }

    static {
        for (Status status : Status.values()) {
            map.append(status.getValue(), status);
        }
    }

    public static Status getStatus(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
