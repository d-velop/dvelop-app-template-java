package com.dvelop.archetype.plugins.storage.memory;

import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestRepository;
import com.dvelop.archetype.VacationRequestState;
import com.dvelop.archetype.VacationType;
import com.dvelop.archetype.plugins.context.TenantHolder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class InMemoryVacationRepository implements VacationRequestRepository {

    Map<String, Map<String, VacationRequest>> storages = new HashMap<>();

    @Inject
    TenantHolder tenantHolder;

    public InMemoryVacationRepository() {
    }

    @Override
    public Collection<VacationRequest> findAll() {
        Map<String, VacationRequest> storage = getStorageForTenant();
        return storage.values();
    }

    @Override
    public VacationRequest findById(String id) {
        Map<String, VacationRequest> storage = getStorageForTenant();
        return storage.get(id);
    }

    @Override
    public void add(VacationRequest vacationRequest) {
        Map<String, VacationRequest> storage = getStorageForTenant();
        storage.put(vacationRequest.getId(), vacationRequest);
    }

    @Override
    public void update(VacationRequest vacationRequest) {
        Map<String, VacationRequest> storage = getStorageForTenant();
        storage.put(vacationRequest.getId(), vacationRequest);
    }

    private Map<String, VacationRequest> getStorageForTenant() {
        storages.computeIfAbsent(tenantHolder.getTenantId(), s -> {
            HashMap<String, VacationRequest> map = new HashMap<>();

            for (int i = 0; i < 3; i++) {
                VacationRequest request = new VacationRequest();
                request.setId(UUID.randomUUID().toString());
                request.setFrom(LocalDate.now());
                request.setTo(LocalDate.now());
                request.setState(VacationRequestState.REQUEST_NEW);
                request.setType(VacationType.TYPE_ANNUAL);
                request.setComment("Vacation tenant "+tenantHolder.getTenantId());
                map.put(request.getId(), request);
            }
            return map;
        });

        return storages.get(tenantHolder.getTenantId());
    }


}
