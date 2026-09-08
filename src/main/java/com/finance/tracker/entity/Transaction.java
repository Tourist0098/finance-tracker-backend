package com.finance.tracker.entity; 
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Transactions")
public class Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    private String category;
    private LocalDate date;

    //GETTERS
    public Long getId(){
        return id;
    }
    public TransactionType getType(){
        return type;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public String getCategory(){
        return category;
    }
    public LocalDate getDate(){
        return date;
    }

    //SETTERS
    public void setType(TransactionType t){
        this.type = t;
    }
    public void setAmount(BigDecimal a){
        this.amount = a;
    }
    public void setCategory(String c){
        this.category = c;
    }
    public void setDate(LocalDate ld){
        this.date = ld;
    }
}