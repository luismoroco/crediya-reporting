package com.crediya.reporting.api;

import com.crediya.common.api.handling.GlobalExceptionFilter;
import com.crediya.reporting.api.config.ReportingPath;
import com.crediya.reporting.model.LoanReport;
import com.crediya.reporting.usecase.ReportingUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RouterRestTest {

  private ReportingUseCase useCase;
  private WebTestClient webTestClient;
  private ReportingPath applicationPath;

  @BeforeEach
  void setUp() {
    useCase = mock(ReportingUseCase.class);
    Handler handler = new Handler(useCase);
    applicationPath = new ReportingPath("/api/v1/reports");
    RouterFunction<?> routes = new RouterRest(handler, new GlobalExceptionFilter(), applicationPath)
      .routerFunction();
    webTestClient = WebTestClient.bindToRouterFunction(routes)
      .build();
  }

  @Test
  void mustGetLoansReport() {
    LoanReport loanReport = new LoanReport();
    loanReport.setLoanReportId("report-id");

    when(useCase.getLoansReport()).thenReturn(Mono.just(loanReport));

    webTestClient.get()
      .uri(applicationPath.getLoansReport())
      .exchange()
      .expectStatus().isOk();
  }
}
