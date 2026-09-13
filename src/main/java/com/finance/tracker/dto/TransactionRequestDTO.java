package com.finance.tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.finance.tracker.entity.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record TransactionRequestDTO(
    @NotNull(message = "Amount is Mandatory")
    @Positive(message = "Amount Must be Greater Than Zero")
    BigDecimal amount,
    
    @NotNull(message = "Transaction Type is Mandatory")
    TransactionType type,
    
    @NotBlank(message = "Category must Not be Empty or Whitespace")
    String category,
    
    @NotNull(message = "Date is Mandatory")
    @PastOrPresent(message = "Transaction Date Can Not be in the Future")
    LocalDate date
) {}
