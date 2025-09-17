package com.crediya.reporting.usecase.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UpdateLoanReportDTO {

  private Long applicationId;
  private Long amount;
}
