package com.crediya.reporting.dynamodb.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanReportEntityTest {

  @Test
  void testNoArgsConstructorAndSettersGetters() {
    LoanReportEntity entity = new LoanReportEntity();

    assertNull(entity.getLoanReportId());
    assertNull(entity.getApprovedCount());
    assertNull(entity.getApprovedTotalAmount());

    entity.setLoanReportId("LR001");
    entity.setApprovedCount(10L);
    entity.setApprovedTotalAmount(5000L);

    assertEquals("LR001", entity.getLoanReportId());
    assertEquals(10L, entity.getApprovedCount());
    assertEquals(5000L, entity.getApprovedTotalAmount());
  }

  @Test
  void testAllArgsConstructor() {
    LoanReportEntity entity = new LoanReportEntity("LR002", 20L, 10000L);

    assertEquals("LR002", entity.getLoanReportId());
    assertEquals(20L, entity.getApprovedCount());
    assertEquals(10000L, entity.getApprovedTotalAmount());
  }

  @Test
  void testPartitionKeyAndAttributesNotNull() {
    LoanReportEntity entity = new LoanReportEntity("LR003", 5L, 2500L);

    assertNotNull(entity.getLoanReportId());
    assertNotNull(entity.getApprovedCount());
    assertNotNull(entity.getApprovedTotalAmount());
  }
}
