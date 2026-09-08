package com.finance.tracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.TransactionType;
import com.finance.tracker.repository.TransactionRepository;

@Service
public class TransactionService{
    private final TransactionRepository repo;
    public TransactionService(TransactionRepository repo){
        this.repo = repo;
    }

    public List<Transaction> getAllTrnansactions(){
        return repo.findAll();
    }
    public Transaction getTransactionById(Long id){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Does Not Exist"));
        return t;
    }
    public Transaction saveTransaction(Transaction t){
        if(t.getAmount() == null || t.getAmount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Transaction Amount Should be Greater Than 0");
        return repo.save(t);
    }
    public void deleteTransaction(Long id){
        if(!repo.existsById(id)) throw new IllegalArgumentException("Transaction with ID: "+id+" is Not Fount");
        repo.deleteById(id);
    }
    public Transaction update(Long id, Transaction nt){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction With ID: "+id+" Was Not Found"));
        t.setAmount(nt.getAmount());
        t.setCategory(nt.getCategory());
        t.setDate(nt.getDate());
        t.setType(nt.getType());
        return repo.save(t);
    }
    public Transaction updateCategory(Long id, String cat){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction With ID: "+id+" Was Not Found"));
        t.setCategory(cat);
        return repo.save(t);
    }
    public Transaction updateAmount(Long id, BigDecimal amnt){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Was Not Found"));
        t.setAmount(amnt);
        return repo.save(t);
    }
    public Transaction updateDate(Long id, LocalDate d){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Was Not Found"));
        t.setDate(d);
        return repo.save(t);
    }
    public Transaction updateType(Long id, TransactionType ty){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Was Not Found"));
        t.setType(ty);
        return repo.save(t);
    }
}