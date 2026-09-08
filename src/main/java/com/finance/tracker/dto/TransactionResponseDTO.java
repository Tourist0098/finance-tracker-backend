package com.finance.tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.finance.tracker.entity.TransactionType;

public record TransactionResponseDTO(
    Long id,
    BigDecimal amount,
    TransactionType type,
    String category,
    LocalDate date
) {}
