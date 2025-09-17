package com.crediya.reporting.dynamodb;

import com.crediya.reporting.dynamodb.helper.TemplateAdapterOperations;
import com.crediya.reporting.model.LoanReport;
import com.crediya.reporting.model.gateway.LoanReportRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;

@Repository
public class LoanReportDynamoDBAdapter extends TemplateAdapterOperations<LoanReport, String, LoanReportEntity> implements LoanReportRepository {

  private static final String TABLE_NAME = "loan_report";

  public LoanReportDynamoDBAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper) {
    super(connectionFactory, mapper, d -> mapper.map(d, LoanReport.class), TABLE_NAME);
  }
}
