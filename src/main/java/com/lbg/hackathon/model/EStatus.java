package com.lbg.hackathon.model;

public enum EStatus {

    CREATED,
    IN_PROGRESS,
    APPROVED;

    public static EStatus findByName(String name) {
        EStatus result = null;
        for (EStatus direction : values()) {
            if (direction.name().equalsIgnoreCase(name)) {
                result = direction;
                break;
            }
        }
        return result;
    }

}
