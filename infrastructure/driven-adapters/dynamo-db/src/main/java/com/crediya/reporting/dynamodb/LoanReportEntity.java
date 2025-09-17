package com.crediya.reporting.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class LoanReportEntity {

  private String loanReportId;
  private Long approvedCount;
  private Long approvedTotalAmount;

  public LoanReportEntity() {
  }

  public LoanReportEntity(String loanReportId, Long approvedCount, Long approvedTotalAmount) {
    this.loanReportId = loanReportId;
    this.approvedCount = approvedCount;
    this.approvedTotalAmount = approvedTotalAmount;
  }

  @DynamoDbPartitionKey
  @DynamoDbAttribute("loan_report_id")
  public String getLoanReportId() {
    return this.loanReportId;
  }

  public void setLoanReportId(String loanReportId) {
    this.loanReportId = loanReportId;
  }

  @DynamoDbAttribute("approved_count")
  public Long getApprovedCount() {
    return approvedCount;
  }

  public void setApprovedCount(Long approvedCount) {
    this.approvedCount = approvedCount;
  }

  @DynamoDbAttribute("approved_total_amount")
  public Long getApprovedTotalAmount() {
    return approvedTotalAmount;
  }

  public void setApprovedTotalAmount(Long approvedTotalAmount) {
    this.approvedTotalAmount = approvedTotalAmount;
  }
}
