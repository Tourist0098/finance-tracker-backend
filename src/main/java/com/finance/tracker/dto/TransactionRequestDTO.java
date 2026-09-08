package com.finance.tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.finance.tracker.entity.TransactionType;

public record TransactionRequestDTO(
    BigDecimal amount,
    TransactionType type,
    String category,
    LocalDate date
) {}
