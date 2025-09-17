package com.crediya.reporting.api;

import com.crediya.common.api.handling.GlobalExceptionFilter;
import com.crediya.reporting.api.config.ReportingPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

    private final Handler handler;
    private final GlobalExceptionFilter filter;
    private final ReportingPath path;

    @RouterOperations({
      @RouterOperation(
        path = "/api/v1/reports",
        produces = { "application/json" },
        beanClass = Handler.class,
        method = RequestMethod.GET,
        beanMethod = "getLoansReport",
        operation = @Operation(
          operationId = "getLoansReport",
          summary = "Get Loans Report",
          security = @SecurityRequirement(name = "bearerAuth"),
          responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
          }
        )
      )
    })
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(GET(this.path.getLoansReport()), this.handler::getLoansReport)
          .filter(filter);
    }
}
