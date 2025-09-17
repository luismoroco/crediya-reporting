package com.crediya.reporting.api;

import com.crediya.common.logging.aspect.AutomaticLogging;
import com.crediya.reporting.usecase.ReportingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final ReportingUseCase useCase;

    @AutomaticLogging
    @PreAuthorize("hasRole('ADVISOR')")
    public Mono<ServerResponse> getLoansReport(ServerRequest serverRequest) {
       return this.useCase.getLoansReport()
          .flatMap(dto -> ServerResponse
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto));
    }
}
