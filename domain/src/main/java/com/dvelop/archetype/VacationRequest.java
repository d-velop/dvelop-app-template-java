package com.dvelop.archetype;

import java.time.LocalDate;

public class VacationRequest {
    String id;
    LocalDate from;
    LocalDate to;

    VacationType type;
    VacationRequestState state;

    java.lang.String comment;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public VacationType getType() {
        return type;
    }

    public void setType(VacationType type) {
        this.type = type;
    }

    public VacationRequestState getState() {
        return state;
    }

    public void setState(VacationRequestState state) {
        this.state = state;
    }

    public java.lang.String getComment() {
        return comment;
    }

    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }
}
