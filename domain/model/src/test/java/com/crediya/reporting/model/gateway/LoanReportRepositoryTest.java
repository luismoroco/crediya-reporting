package com.crediya.reporting.model.gateway;

import com.crediya.reporting.model.LoanReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoanReportRepositoryTest {

  private LoanReportRepository loanReportRepository;

  @BeforeEach
  void setUp() {
    loanReportRepository = Mockito.mock(LoanReportRepository.class);
  }

  @Test
  void testSaveLoanReport() {
    LoanReport report = LoanReport.builder()
      .loanReportId("R1")
      .approvedCount(5L)
      .approvedTotalAmount(10000L)
      .build();

    when(loanReportRepository.save(any(LoanReport.class))).thenReturn(Mono.just(report));

    StepVerifier.create(loanReportRepository.save(report))
      .expectNextMatches(r ->
        r.getLoanReportId().equals("R1") &&
          r.getApprovedCount().equals(5L) &&
          r.getApprovedTotalAmount().equals(10000L)
      )
      .verifyComplete();

    verify(loanReportRepository, times(1)).save(report);
  }

  @Test
  void testGetByIdLoanReport() {
    LoanReport report = LoanReport.builder()
      .loanReportId("R2")
      .approvedCount(10L)
      .approvedTotalAmount(20000L)
      .build();

    when(loanReportRepository.getById("R2")).thenReturn(Mono.just(report));

    StepVerifier.create(loanReportRepository.getById("R2"))
      .expectNextMatches(r ->
        r.getLoanReportId().equals("R2") &&
          r.getApprovedCount().equals(10L) &&
          r.getApprovedTotalAmount().equals(20000L)
      )
      .verifyComplete();

    verify(loanReportRepository, times(1)).getById("R2");
  }
}
