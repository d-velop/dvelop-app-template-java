package com.dvelop.archetype;

public enum VacationRequestState {

    REQUEST_NEW("new"),
    REQUEST_ACCEPTED("accepted"),
    REQUEST_REJECTED("rejected"),
    REQUEST_CANCELLED("cancelled");

    private String text;

    VacationRequestState(String text) {
        this.text = text;
    }

    public static VacationRequestState from(String value) {
        switch (value) {
            case "new":
                return REQUEST_NEW;
            case "accepted":
                return REQUEST_ACCEPTED;
            case "rejected":
                return REQUEST_REJECTED;
            case "cancelled":
                return REQUEST_CANCELLED;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
