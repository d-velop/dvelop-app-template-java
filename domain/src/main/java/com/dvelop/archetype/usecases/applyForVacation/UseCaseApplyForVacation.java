package com.dvelop.archetype.usecases.applyForVacation;

import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestRepository;

import javax.inject.Inject;
import java.util.UUID;

public class UseCaseApplyForVacation {

    private VacationRequestRepository repository;

    @Inject
    public UseCaseApplyForVacation(VacationRequestRepository repository) {
        this.repository = repository;
    }

    public void execute(VacationRequest request) {
        repository.add(request);
    }
}
