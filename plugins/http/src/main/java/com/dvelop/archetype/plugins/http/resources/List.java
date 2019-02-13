package com.dvelop.archetype.plugins.http.resources;


import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestRepository;
import com.dvelop.archetype.VacationRequestState;
import com.dvelop.archetype.VacationType;
import com.dvelop.archetype.plugins.context.TenantHolder;
import com.dvelop.archetype.plugins.http.MediaType;
import com.dvelop.archetype.usecases.applyForVacation.UseCaseApplyForVacation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;
import java.util.UUID;

@RequestScoped
@Path(List.PATH)
public class List {
    public static final String PATH = "/requests";


    public static class ListDto {
        Collection<VacationRequest> requests;

        public Collection<VacationRequest> getRequests() {
            return requests;
        }

        public void setRequests(Collection<VacationRequest> requests) {
            this.requests = requests;
        }
    }

    @Inject
    TenantHolder tenant;

    @Inject
    VacationRequestRepository repository;

    @Inject
    UseCaseApplyForVacation useCaseApplyForVacation;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response listRequests( ){

        ListDto dto = new ListDto();
        if( repository != null){
            dto.setRequests(repository.findAll());
        } else {
            System.err.println("No repository!!!!");
        }

        return Response.ok(dto).build();

    }

    @POST
    public Response newRequest(){
        VacationRequest request = new VacationRequest();
        request.setId(UUID.randomUUID().toString());
        request.setState(VacationRequestState.REQUEST_NEW);
        request.setType(VacationType.TYPE_ANNUAL);

        useCaseApplyForVacation.execute(request);

        return Response.status(Response.Status.CREATED)
                .header("Location", tenant.getBaseUri()+"/vacationprocess4j/requests/"+request.getId())
                .build();
    }

}
