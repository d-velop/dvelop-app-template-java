package com.dvelop.archetype;

import java.util.Collection;

public interface VacationRequestRepository {

    Collection<VacationRequest> findAll();

    VacationRequest findById(String id);

    void add(VacationRequest vacationRequest);

    void update(VacationRequest vacationRequest);

}
