package com.crediya.reporting.api.config;

import com.crediya.common.exc.BadRequestException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Objects;

import static com.crediya.common.LogCatalog.ENTITY_NOT_FOUND;

@Component
public class WebContextFilter implements WebFilter {

  public static final String IDENTITY_CARD_NUMBER = "identityCardNumber";

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return exchange.getPrincipal()
      .cast(JwtAuthenticationToken.class)
      .flatMap(authToken -> {
        String identityCardNumber = authToken.getToken().getClaimAsString(IDENTITY_CARD_NUMBER);
        if (Objects.isNull(identityCardNumber)) {
          return Mono.error(new BadRequestException(ENTITY_NOT_FOUND.of(IDENTITY_CARD_NUMBER)));
        }

        return chain.filter(exchange)
          .contextWrite(Context.of(IDENTITY_CARD_NUMBER, identityCardNumber));
      })
      .switchIfEmpty(chain.filter(exchange));
  }
}
