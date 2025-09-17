package com.crediya.reporting.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "route.path")
public record ReportingPath(
  String getLoansReport
) {
}
