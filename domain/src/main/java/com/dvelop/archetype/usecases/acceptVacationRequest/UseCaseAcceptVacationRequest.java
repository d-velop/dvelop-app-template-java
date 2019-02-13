package com.dvelop.archetype.usecases.acceptVacationRequest;

import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestException;
import com.dvelop.archetype.VacationRequestRepository;
import com.dvelop.archetype.VacationRequestState;

import javax.inject.Inject;

public class UseCaseAcceptVacationRequest {

    VacationRequestRepository repository;

    @Inject
    public UseCaseAcceptVacationRequest(VacationRequestRepository repository) {
        this.repository = repository;
    }


    public void execute(String vacationRequestId) throws VacationRequestException {

        VacationRequest request = repository.findById(vacationRequestId);

        if (request == null) {
            throw new VacationRequestException("invalid id");
        }

        if (request.getState() != VacationRequestState.REQUEST_NEW) {
            throw new VacationRequestException("can only accept open requests");
        }

        request.setState(VacationRequestState.REQUEST_ACCEPTED);

        repository.update(request);
    }

}
