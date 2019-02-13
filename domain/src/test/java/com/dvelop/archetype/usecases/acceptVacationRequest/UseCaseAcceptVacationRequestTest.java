package com.dvelop.archetype.usecases.acceptVacationRequest;

import com.dvelop.archetype.*;
import com.dvelop.archetype.VacationRequestState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UseCaseAcceptVacationRequestTest {

    @Mock
    private VacationRequestRepository requestRepository;

    @Captor
    private ArgumentCaptor<VacationRequest> savedRequest;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void existingRequest_requestStateIsUpdated() throws VacationRequestException {
        java.lang.String requestId = "4711";

        VacationRequest givenRequest = new VacationRequest();
        givenRequest.setId(requestId);
        givenRequest.setFrom(LocalDate.now());
        givenRequest.setTo(LocalDate.now().plusDays(2));
        givenRequest.setState(VacationRequestState.REQUEST_NEW);
        givenRequest.setType(VacationType.TYPE_ANNUAL);
        givenRequest.setComment("Just for fun");

        when(requestRepository.findById(anyString())).thenReturn(givenRequest);

        UseCaseAcceptVacationRequest usecase = new UseCaseAcceptVacationRequest(requestRepository);
        usecase.execute(requestId);

        verify(requestRepository).findById(requestId);
        verify(requestRepository).update(savedRequest.capture());

        VacationRequest updatedRequest = savedRequest.getValue();

        assertThat(updatedRequest.getState(), is(VacationRequestState.REQUEST_ACCEPTED));
    }

    @Test
    void nonExistingRequest_revokesWithException(){
        java.lang.String invalidRequestId = "4712";

        when(requestRepository.findById(anyString())).thenReturn(null);

        UseCaseAcceptVacationRequest usecase = new UseCaseAcceptVacationRequest(requestRepository);

        assertThrows(VacationRequestException.class, () -> usecase.execute(invalidRequestId));
    }

    @Test
    void requestAlreadyAccepted_cannotAcceptAgain(){
        java.lang.String requestId = "4711";

        VacationRequest givenRequest = new VacationRequest();
        givenRequest.setId(requestId);
        givenRequest.setFrom(LocalDate.now());
        givenRequest.setTo(LocalDate.now().plusDays(2));
        givenRequest.setState(VacationRequestState.REQUEST_ACCEPTED);
        givenRequest.setType(VacationType.TYPE_ANNUAL);
        givenRequest.setComment("Just for fun");

        when(requestRepository.findById(requestId)).thenReturn(givenRequest);

        UseCaseAcceptVacationRequest usecase = new UseCaseAcceptVacationRequest(requestRepository);

        assertThrows(VacationRequestException.class, () -> usecase.execute(requestId));
    }

}