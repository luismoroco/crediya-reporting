package com.crediya.reporting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoanReportTest {

  @Test
  void testBuilderAndGettersSetters() {
    LoanReport report = LoanReport.builder()
      .loanReportId("LR001")
      .approvedCount(10L)
      .approvedTotalAmount(5000L)
      .build();

    assertEquals("LR001", report.getLoanReportId());
    assertEquals(10L, report.getApprovedCount());
    assertEquals(5000L, report.getApprovedTotalAmount());

    report.setLoanReportId("LR002");
    report.setApprovedCount(20L);
    report.setApprovedTotalAmount(10000L);

    assertEquals("LR002", report.getLoanReportId());
    assertEquals(20L, report.getApprovedCount());
    assertEquals(10000L, report.getApprovedTotalAmount());
  }

  @Test
  void testNoArgsConstructor() {
    LoanReport report = new LoanReport();
    assertNull(report.getLoanReportId());
    assertNull(report.getApprovedCount());
    assertNull(report.getApprovedTotalAmount());
  }

  @Test
  void testAllArgsConstructor() {
    LoanReport report = new LoanReport("LR003", 5L, 2500L);
    assertEquals("LR003", report.getLoanReportId());
    assertEquals(5L, report.getApprovedCount());
    assertEquals(2500L, report.getApprovedTotalAmount());
  }

  @Test
  void testEnumToString() {
    assertEquals("LOAN REPORT ID", LoanReport.Field.LOAN_REPORT_ID.toString());
    assertEquals("APPROVED COUNT", LoanReport.Field.APPROVED_COUNT.toString());
    assertEquals("APPROVED TOTAL AMOUNT", LoanReport.Field.APPROVED_TOTAL_AMOUNT.toString());
  }
}
