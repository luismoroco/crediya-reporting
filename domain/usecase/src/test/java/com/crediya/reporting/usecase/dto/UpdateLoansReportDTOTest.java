package com.crediya.reporting.usecase.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateLoansReportDTOTest {

  @Test
  void testBuilderAndGetters() {
    UpdateLoansReportDTO dto = UpdateLoansReportDTO.builder()
      .applicationId(1L)
      .amount(5000L)
      .build();

    assertEquals(1L, dto.getApplicationId());
    assertEquals(5000L, dto.getAmount());
  }

  @Test
  void testSetters() {
    UpdateLoansReportDTO dto = new UpdateLoansReportDTO();
    dto.setApplicationId(2L);
    dto.setAmount(10000L);

    assertEquals(2L, dto.getApplicationId());
    assertEquals(10000L, dto.getAmount());
  }

  @Test
  void testToString() {
    UpdateLoansReportDTO dto = UpdateLoansReportDTO.builder()
      .applicationId(3L)
      .amount(15000L)
      .build();

    String str = dto.toString();
    assertTrue(str.contains("applicationId=3"));
    assertTrue(str.contains("amount=15000"));
  }
}
