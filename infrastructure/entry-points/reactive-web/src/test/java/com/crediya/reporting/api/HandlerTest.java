package com.crediya.reporting.api;

import com.crediya.reporting.model.LoanReport;
import com.crediya.reporting.usecase.ReportingUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

public class HandlerTest {

  @InjectMocks
  private Handler handler;

  @Mock
  private ReportingUseCase useCase;

  @Mock
  private ServerRequest serverRequest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testStartApplication() {

    LoanReport loanReport = new LoanReport();
    loanReport.setLoanReportId("report-id");

    when(useCase.getLoansReport()).thenReturn(Mono.just(loanReport));

    Mono<ServerResponse> responseMono = handler.getLoansReport(serverRequest);

    StepVerifier.create(responseMono)
      .assertNext(response -> {
        assert response.statusCode().equals(HttpStatus.OK);
        assert response.headers().getContentType().equals(MediaType.APPLICATION_JSON);
      })
      .verifyComplete();
  }
}
