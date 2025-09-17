package com.crediya.reporting.usecase;

import com.crediya.common.exc.NotFoundException;
import com.crediya.common.logging.Logger;
import com.crediya.common.validation.exc.ValidationException;
import com.crediya.reporting.model.LoanReport;
import com.crediya.reporting.model.gateway.LoanReportRepository;
import com.crediya.reporting.usecase.dto.UpdateLoansReportDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class ReportingUseCaseTest {

  private LoanReportRepository repository;
  private Logger logger;
  private ReportingUseCase reportingUseCase;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(LoanReportRepository.class);
    logger = Mockito.mock(Logger.class);
    reportingUseCase = new ReportingUseCase(repository, logger);
  }

  @Test
  void testGetLoansReportSuccess() {
    LoanReport report = LoanReport.builder()
      .loanReportId("loan-report")
      .approvedCount(5L)
      .approvedTotalAmount(10000L)
      .build();

    when(repository.getById("loan-report")).thenReturn(Mono.just(report));

    StepVerifier.create(reportingUseCase.getLoansReport())
      .expectNextMatches(r ->
        r.getLoanReportId().equals("loan-report") &&
          r.getApprovedCount().equals(5L) &&
          r.getApprovedTotalAmount().equals(10000L)
      )
      .verifyComplete();

    verify(repository, times(1)).getById("loan-report");
  }

  @Test
  void testGetLoansReportNotFound() {
    when(repository.getById("loan-report")).thenReturn(Mono.empty());

    StepVerifier.create(reportingUseCase.getLoansReport())
      .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
        throwable.getMessage().contains("loan-report"))
      .verify();

    verify(logger, times(1)).error(anyString(), any());
  }

  @Test
  void testUpdateLoansReportSuccess() {
    UpdateLoansReportDTO dto = UpdateLoansReportDTO.builder()
      .applicationId(1L)
      .amount(5000L)
      .build();

    LoanReport existingReport = LoanReport.builder()
      .loanReportId("loan-report")
      .approvedCount(5L)
      .approvedTotalAmount(10000L)
      .build();

    LoanReport updatedReport = LoanReport.builder()
      .loanReportId("loan-report")
      .approvedCount(6L)
      .approvedTotalAmount(15000L)
      .build();

    when(repository.getById("loan-report")).thenReturn(Mono.just(existingReport));
    when(repository.save(any(LoanReport.class))).thenReturn(Mono.just(updatedReport));

    StepVerifier.create(reportingUseCase.updateLoansReport(dto))
      .expectNextMatches(r ->
        r.getApprovedCount().equals(6L) &&
          r.getApprovedTotalAmount().equals(15000L)
      )
      .verifyComplete();

    verify(repository, times(1)).getById("loan-report");
    verify(repository, times(1)).save(any(LoanReport.class));
  }

  @Test
  void testValidateUpdateLoansReportDTOConstraintsSuccess() {
    UpdateLoansReportDTO dto = UpdateLoansReportDTO.builder()
      .applicationId(1L)
      .amount(5000L)
      .build();

    StepVerifier.create(ReportingUseCase.validateUpdateLoansReportDTOConstraints(dto))
      .verifyComplete();
  }

  @Test
  void testValidateUpdateLoansReportDTOConstraintsFailure() {
    UpdateLoansReportDTO dto = UpdateLoansReportDTO.builder()
      .applicationId(null)
      .amount(null)
      .build();

    StepVerifier.create(ReportingUseCase.validateUpdateLoansReportDTOConstraints(dto))
      .expectErrorMatches(throwable -> throwable instanceof ValidationException)
      .verify();
  }
}
