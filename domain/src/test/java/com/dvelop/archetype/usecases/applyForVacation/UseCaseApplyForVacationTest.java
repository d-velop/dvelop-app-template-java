package com.dvelop.archetype.usecases.applyForVacation;

import com.dvelop.archetype.VacationRequest;
import com.dvelop.archetype.VacationRequestRepository;
import com.dvelop.archetype.VacationRequestState;
import com.dvelop.archetype.VacationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;

class UseCaseApplyForVacationTest {

    @Mock
    private VacationRequestRepository requestRepository;

    @Captor
    private ArgumentCaptor<VacationRequest> savedRequest;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void newAndValidRequest_newRequestIdStored() {
        VacationRequest givenRequest = new VacationRequest();
        givenRequest.setId(UUID.randomUUID().toString());
        givenRequest.setFrom(LocalDate.now());
        givenRequest.setTo(LocalDate.now().plusDays(2));
        givenRequest.setState(VacationRequestState.REQUEST_NEW);
        givenRequest.setType(VacationType.TYPE_ANNUAL);
        givenRequest.setComment("Just for fun");

        UseCaseApplyForVacation usecase = new UseCaseApplyForVacation(requestRepository);

        usecase.execute(givenRequest);

        verify(requestRepository).add(savedRequest.capture());

        VacationRequest savedRequestValue = savedRequest.getValue();

        assertThat(savedRequestValue.getId(), is(notNullValue()));

        assertThat(savedRequestValue.getFrom(), is(equalTo(givenRequest.getFrom())));
        assertThat(savedRequestValue.getTo(), is(equalTo(givenRequest.getTo())));
        assertThat(savedRequestValue.getType(), is(equalTo(givenRequest.getType())));
        assertThat(savedRequestValue.getState(), is(equalTo(givenRequest.getState())));
        assertThat(savedRequestValue.getComment(), is(equalTo(givenRequest.getComment())));
    }


}