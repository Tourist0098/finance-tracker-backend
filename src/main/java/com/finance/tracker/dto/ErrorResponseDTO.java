package com.finance.tracker.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO(
    LocalDateTime timestamp,
    int status,
    String error,
    Map<String, String> validationErrors
) {}
