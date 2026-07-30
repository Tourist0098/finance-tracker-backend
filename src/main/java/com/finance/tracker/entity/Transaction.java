package com.finance.tracker.entity;
import java.time.LocalDate;

import com.finance.tracker.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;
    private double amount;
    private String category;
    private LocalDate date;

    public Transaction(){}
    public Transaction(TransactionType type, double amount, String category, LocalDate date){
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    //GETTERS and SETTTERS
    public Long getId(){
        return id;
    }
    public TransactionType getType(){
        return type;
    }
    public void setType(TransactionType type){
        this.type = type;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double amnt){
        this.amount = amnt;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setCategory(LocalDate date){
        this.date = date;
    }
}