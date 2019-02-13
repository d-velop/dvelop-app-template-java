package com.dvelop.archetype.plugins.http.resources;

import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestException;
import com.dvelop.archetype.VacationRequestRepository;
import com.dvelop.archetype.VacationRequestState;
import com.dvelop.archetype.plugins.http.MediaType;
import com.dvelop.archetype.plugins.http.dto.StateUpdateDto;
import com.dvelop.archetype.plugins.http.dto.VacationRequestDto;
import com.dvelop.archetype.usecases.acceptVacationRequest.UseCaseAcceptVacationRequest;
import com.dvelop.archetype.usecases.applyForVacation.UseCaseApplyForVacation;
import com.dvelop.archetype.usecases.cancelVacation.UseCaseCancelVacationRequest;
import com.dvelop.archetype.usecases.rejectRequest.UseCaseRejectVacationRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path(Request.PATH)
public class Request {
    public static final String PATH = "/requests/{id}";

    @Inject
    VacationRequestRepository repository;

    @Inject
    UseCaseAcceptVacationRequest useCaseAcceptVacationRequest;

    @Inject
    UseCaseRejectVacationRequest useCaseRejectVacationRequest;

    @Inject
    UseCaseCancelVacationRequest useCaseCancelVacationRequest;

    @Inject
    UseCaseApplyForVacation useCaseApplyForVacation;

    @GET
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, "application/hal+json"})
    public Response getRequest(@PathParam("id") String id) {
        VacationRequest request = repository.findById(id);

        if (request == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            VacationRequestDto dto = new VacationRequestDto(request);
            return Response.ok(dto).build();
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_HAL_JSON})
    public Response putRequest(@PathParam("id") String id, VacationRequestDto vacationRequestDto){

        vacationRequestDto.setId(id);
        VacationRequest vacationRequest = vacationRequestDto.toDomain();
        useCaseApplyForVacation.execute(vacationRequest);

        return Response.accepted().build();
    }

    @PATCH
    @Consumes("application/merge-patch+json")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_HAL_JSON})
    public Response patchRequest(@PathParam("id") String id, StateUpdateDto dto) {
        VacationRequestState state = VacationRequestState.from(dto.getState());

        try {
            switch (state) {

                case REQUEST_NEW:
                    return Response.status(Response.Status.BAD_REQUEST).entity("can not actively set a request to 'new'").build();

                case REQUEST_ACCEPTED:
                    useCaseAcceptVacationRequest.execute(id);
                    break;

                case REQUEST_REJECTED:
                    useCaseRejectVacationRequest.execute(id);
                    break;

                case REQUEST_CANCELLED:
                    useCaseCancelVacationRequest.execute(id);
                    break;
            }
            return Response.accepted().build();
        } catch (VacationRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("failed to update state: "+e.getMessage()).build();
        }


    }
}
