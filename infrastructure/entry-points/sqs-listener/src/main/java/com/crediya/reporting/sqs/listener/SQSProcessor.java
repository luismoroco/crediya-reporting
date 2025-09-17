package com.crediya.reporting.sqs.listener;

import com.crediya.common.logging.Logger;
import com.crediya.reporting.usecase.ReportingUseCase;
import com.crediya.reporting.usecase.dto.UpdateLoanReportDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {

    private final ReportingUseCase useCase;
    private final ObjectMapper mapper;
    private final Logger logger;

    @Override
    public Mono<Void> apply(Message message) {
        this.logger.info("Message received [payload={}]", message.body());

        return Mono.fromCallable(() -> this.mapper.readValue(message.body(), UpdateLoanReportDTO.class))
          .flatMap(this.useCase::updateLoanReport)
          .then();
    }
}
