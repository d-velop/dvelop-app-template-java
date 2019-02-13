package com.dvelop.archetype;

public enum VacationType {
    TYPE_ANNUAL("annual"),
    TYPE_SPECIAL("special"),
    TYPE_COMPENSATORY("compensatory");

    private String text;

    VacationType(String text) {
        this.text = text;
    }

    public static VacationType from(String value) {
        switch (value) {
            case "annual":
                return TYPE_ANNUAL;
            case "special":
                return TYPE_SPECIAL;
            case "compensatory":
                return TYPE_COMPENSATORY;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
