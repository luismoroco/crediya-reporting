package com.crediya.reporting.model.gateway;

import com.crediya.reporting.model.LoanReport;
import reactor.core.publisher.Mono;

public interface LoanReportRepository {
  Mono<LoanReport> getById(String loanReportId);

  Mono<LoanReport> save(LoanReport loanReport);
}
