package com.crediya.reporting.api;

import com.crediya.reporting.usecase.ReportingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final ReportingUseCase useCase;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
       return this.useCase.getReport()
          .flatMap(dto -> ServerResponse
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto));
    }
}
