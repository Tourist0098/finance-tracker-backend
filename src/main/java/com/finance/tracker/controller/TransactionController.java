package com.finance.tracker.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.dto.TransactionRequestDTO;
import com.finance.tracker.dto.TransactionResponseDTO;
import com.finance.tracker.entity.TransactionType;
import com.finance.tracker.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
@Validated
public class  TransactionController{
    private final TransactionService serv;
    public TransactionController(TransactionService serv){
        this.serv = serv;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO t){
        return new ResponseEntity<>(serv.saveTransaction(t), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(){
        return new ResponseEntity<>(serv.getAllTransactions(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransaction(@PathVariable Long id){
        return new ResponseEntity<>(serv.getTransactionById(id), HttpStatus.OK);
    }
    @GetMapping("/{id}/amount")
    public ResponseEntity<BigDecimal> getAmountById(@PathVariable Long id){
        return new ResponseEntity<>(serv.getTransactionById(id).amount(), HttpStatus.OK);
    }
    @GetMapping("/{id}/category")
    public ResponseEntity<String> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(serv.getTransactionById(id).category(), HttpStatus.OK);
    }
    @GetMapping("/{id}/type")
    public ResponseEntity<TransactionType> getTypeById(@PathVariable Long id){
        return new ResponseEntity<>(serv.getTransactionById(id).type(), HttpStatus.OK);
    }
    @GetMapping("/{id}/date")
    public ResponseEntity<LocalDate> getDateById(@PathVariable Long id){
        return new ResponseEntity<>(serv.getTransactionById(id).date(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TransactionRequestDTO t){
        return new ResponseEntity<>(serv.update(id, t), HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/category")
    public ResponseEntity<TransactionResponseDTO> updateCategory(@PathVariable Long id, @RequestBody String cat){
        return new ResponseEntity<>(serv.updateCategory(id, cat), HttpStatus.OK);
    }
    @PatchMapping("/{id}/amount")
    public ResponseEntity<TransactionResponseDTO> updateAmount(@PathVariable Long id, @RequestBody BigDecimal amnt){
        return new ResponseEntity<>(serv.updateAmount(id, amnt), HttpStatus.OK);
    }
    @PatchMapping("/{id}/type")
    public ResponseEntity<TransactionResponseDTO> updateType(@PathVariable Long id, @RequestBody TransactionType type){
        return new ResponseEntity<>(serv.updateType(id, type), HttpStatus.OK);
    }
    @PatchMapping("/{id}/date")
    public ResponseEntity<TransactionResponseDTO> updateDate(@PathVariable Long id,  @RequestBody LocalDate date){
        return new ResponseEntity<>(serv.updateDate(id, date), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        serv.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}