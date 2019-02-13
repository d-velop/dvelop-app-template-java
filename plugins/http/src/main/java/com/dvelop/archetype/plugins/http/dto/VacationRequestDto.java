package com.dvelop.archetype.plugins.http.dto;

import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestState;
import com.dvelop.archetype.VacationType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class VacationRequestDto {
    String id;

    String from;
    String to;

    String type = VacationType.TYPE_ANNUAL.toString();
    String state = VacationRequestState.REQUEST_NEW.toString();
    String comment = "";

    public VacationRequestDto() {
    }

    public VacationRequestDto(VacationRequest request) {
        id = request.getId();
        if (request.getFrom() != null) {
            from = request.getFrom().toString();
        }
        if (request.getTo() != null) {
            to = request.getTo().toString();
        }
        type = request.getType() != null ? request.getType().toString() : VacationType.TYPE_ANNUAL.toString();
        state = request.getState() != null ? request.getState().toString() : VacationRequestState.REQUEST_NEW.toString();
        comment = request.getComment();
    }

    public VacationRequest toDomain() {
        // cut of possible time on ISO8601 string and convert to local date
        // this should probably not convert to local, but to utc date
        // someone should write his dissertation about this...
        LocalDate fromDate = ZonedDateTime.parse(from).toLocalDate();
        LocalDate toDate = ZonedDateTime.parse(to).toLocalDate();

        VacationRequest vacationRequest = new VacationRequest();
        vacationRequest.setId(id);
        vacationRequest.setFrom(fromDate);
        vacationRequest.setTo(toDate);
        vacationRequest.setType(VacationType.from(type));
        vacationRequest.setState(VacationRequestState.from(state));
        vacationRequest.setComment(comment);
        return vacationRequest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
