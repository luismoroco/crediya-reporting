package com.crediya.reporting.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanReport {

  private String loanReportId;
  private Long approvedCount;
  private Long approvedTotalAmount;

  public enum Field {

    LOAN_REPORT_ID,
    APPROVED_COUNT,
    APPROVED_TOTAL_AMOUNT;

    @Override
    public String toString() {
      return this.name().replaceAll("_", " ");
    }
  }
}
