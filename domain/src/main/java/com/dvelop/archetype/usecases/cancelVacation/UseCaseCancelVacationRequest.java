package com.dvelop.archetype.usecases.cancelVacation;

import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestException;
import com.dvelop.archetype.VacationRequestRepository;
import com.dvelop.archetype.VacationRequestState;

import javax.inject.Inject;

public class UseCaseCancelVacationRequest {

    VacationRequestRepository repository;

    @Inject
    public UseCaseCancelVacationRequest(VacationRequestRepository repository) {
        this.repository = repository;
    }


    public void execute(java.lang.String vacationRequestId) throws VacationRequestException {

        VacationRequest request = repository.findById(vacationRequestId);

        if (request == null){
            throw new VacationRequestException("invalid id");
        }

        request.setState(VacationRequestState.REQUEST_CANCELLED);

        repository.update(request);
    }


}
