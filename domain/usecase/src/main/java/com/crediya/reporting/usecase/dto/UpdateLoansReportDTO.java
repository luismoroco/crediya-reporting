package com.crediya.reporting.usecase.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UpdateLoansReportDTO {

  private Long applicationId;
  private Long amount;
}
