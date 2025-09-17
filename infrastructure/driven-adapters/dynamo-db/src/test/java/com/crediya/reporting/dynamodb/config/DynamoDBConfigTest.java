package com.crediya.reporting.dynamodb.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.metrics.MetricPublisher;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(MockitoExtension.class)
class DynamoDBConfigTest {
//
//
//    @Mock
//    private MetricPublisher publisher;
//
//    @Mock
//    private DynamoDbAsyncClient dynamoDbAsyncClient;
//
//    private final DynamoDBConfig dynamoDBConfig = new DynamoDBConfig();
//
//    @Test
//    void testAmazonDynamoDB() {
//        DynamoDbAsyncClient client = dynamoDBConfig.amazonDynamoDB(publisher);
//        assertNotNull(client, "El bean amazonDynamoDB no debería ser null");
//    }
//
//    @Test
//    void testGetDynamoDbEnhancedAsyncClient() {
//        DynamoDbEnhancedAsyncClient enhancedClient = dynamoDBConfig.getDynamoDbEnhancedAsyncClient(dynamoDbAsyncClient);
//        assertNotNull(enhancedClient, "El bean getDynamoDbEnhancedAsyncClient no debería ser null");
//    }
}
