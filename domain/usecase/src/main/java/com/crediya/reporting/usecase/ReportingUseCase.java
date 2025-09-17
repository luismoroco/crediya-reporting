package com.crediya.reporting.usecase;

import com.crediya.common.exc.NotFoundException;
import com.crediya.common.logging.Logger;
import com.crediya.reporting.model.LoanReport;
import com.crediya.reporting.model.gateway.LoanReportRepository;
import com.crediya.reporting.usecase.dto.UpdateLoansReportDTO;
import com.crediya.common.validation.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.crediya.common.LogCatalog.ENTITY_NOT_FOUND;
import static com.crediya.reporting.model.LoanReport.Field.*;

@RequiredArgsConstructor
public class ReportingUseCase {

  private static final String LOAN_REPORT_IDENTIFIER = "loan-report";
  private static final String AMOUNT = "amount";
  private static final String APPLICATION_ID = "applicationId";

  private final LoanReportRepository repository;
  private final Logger logger;

  public Mono<LoanReport> getLoansReport() {
    return this.repository.getById(LOAN_REPORT_IDENTIFIER)
      .switchIfEmpty(Mono.defer(() -> {
        this.logger.error("Loan Report not found [loanReportId={}]", LOAN_REPORT_IDENTIFIER);
        return Mono.error(new NotFoundException(ENTITY_NOT_FOUND.of(LOAN_REPORT_ID, LOAN_REPORT_IDENTIFIER)));
      }));
  }

  public Mono<LoanReport> updateLoansReport(UpdateLoansReportDTO dto) {
    return validateUpdateLoansReportDTOConstraints(dto)
      .then(this.getLoansReport())
      .flatMap(loanReport -> {
        loanReport.setApprovedCount(loanReport.getApprovedCount() + 1);
        loanReport.setApprovedTotalAmount(loanReport.getApprovedTotalAmount() + dto.getAmount());

        return repository.save(loanReport);
      })
      .doOnError(error -> this.logger.error("Error updating loan report [args={}][error={}]", dto, error.getMessage()));
  }

  public static Mono<Void> validateUpdateLoansReportDTOConstraints(UpdateLoansReportDTO dto) {
    return ValidatorUtils.nonNull(AMOUNT, dto.getAmount())
      .then(ValidatorUtils.nonNull(APPLICATION_ID, dto.getApplicationId()));
  }
}
