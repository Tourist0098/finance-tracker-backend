package com.finance.tracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.TransactionType;
import com.finance.tracker.service.TransactionService;

@RestController
@RequestMapping("/api/v1/Tansaction")
public class  TransactionController{
    private final TransactionService serv;
    public TransactionController(TransactionService serv){
        this.serv = serv;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction t){
        Transaction s = serv.saveTransaction(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> li = serv.getAllTrnansactions();
        return ResponseEntity.ok(li);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id){
        Transaction t = serv.getTransactionById(id);
        return ResponseEntity.ok(t);
    }
    @GetMapping("/{id}/amount")
    public ResponseEntity<Double> getAmountById(@PathVariable Long id){
        Transaction t = serv.getTransactionById(id);
        return ResponseEntity.ok(t.getAmount());
    }
    @GetMapping("/{id}/category")
    public ResponseEntity<String> getCategoryById(@PathVariable Long id){
        Transaction t = serv.getTransactionById(id);
        return ResponseEntity.ok(t.getCategory());
    }
    @GetMapping("/{id}/type")
    public ResponseEntity<TransactionType> getTypeById(@PathVariable Long id){
        Transaction t = serv.getTransactionById(id);
        return ResponseEntity.ok(t.getType());
    }
    @GetMapping("/{id}/date")
    public ResponseEntity<LocalDate> getDateById(@PathVariable Long id){
        Transaction t = serv.getTransactionById(id);
        return ResponseEntity.ok(t.getDate());        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody Transaction t){
        return ResponseEntity.ok(serv.update(id, t));
    }
    
    @PatchMapping("/{id}/category")
    public ResponseEntity<Transaction> updateCategory(@PathVariable Long id, @RequestBody String cat){
        return ResponseEntity.ok(serv.updateCategory(id, cat));
    }
    @PatchMapping("/{id}/amount")
    public ResponseEntity<Transaction> updateAmount(@PathVariable Long Id, @RequestBody Double amnt){
        return ResponseEntity.ok(serv.updateAmount(Id, amnt));
    }
    @PatchMapping("/{id}/type")
    public ResponseEntity<Transaction> updateType(@PathVariable Long id, @RequestBody TransactionType ty){
        return ResponseEntity.ok(serv.updateType(id, ty));
    }
    @PatchMapping("/{id}/date")
    public ResponseEntity<Transaction> updateDate(@PathVariable Long id, @RequestBody LocalDate d){
        return ResponseEntity.ok(serv.updateDate(id, d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        serv.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}