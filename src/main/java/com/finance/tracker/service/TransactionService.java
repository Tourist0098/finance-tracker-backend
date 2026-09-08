package com.finance.tracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finance.tracker.dto.TransactionRequestDTO;
import com.finance.tracker.dto.TransactionResponseDTO;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.TransactionType;
import com.finance.tracker.repository.TransactionRepository;

@Service
public class TransactionService{
    private final TransactionRepository repo;
    public TransactionService(TransactionRepository repo){
        this.repo = repo;
    }

    private TransactionResponseDTO mapToResponseDTO(Transaction t){
        return new TransactionResponseDTO(
            t.getId(),
            t.getAmount(),
            t.getType(),
            t.getCategory(),
            t.getDate()
        );
    }

    public List<TransactionResponseDTO> getAllTransactions(){
        return repo.findAll().stream().map(this::mapToResponseDTO).toList();
    }
    public TransactionResponseDTO  getTransactionById(Long id){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Does Not Exist"));
        return mapToResponseDTO(t);
    }
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO request){
        if(request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Transaction Amount is Not Valid");

        Transaction t = new Transaction();
        t.setAmount(request.amount());
        t.setCategory(request.category());
        t.setType(request.type());
        t.setDate(request.date());

        Transaction s = repo.save(t);

        return mapToResponseDTO(s);
    }
    public void deleteTransaction(Long id){
        if(!repo.existsById(id)) throw new IllegalArgumentException("Transaction with ID: "+id+" is Not Fount");
        repo.deleteById(id);
    }
    public TransactionResponseDTO update(Long id, TransactionRequestDTO request){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction With ID: "+id+" Was Not Found"));
        t.setAmount(request.amount());
        t.setCategory(request.category());
        t.setDate(request.date());
        t.setType(request.type());
        repo.save(t);
        return mapToResponseDTO(t);
    }
    public TransactionResponseDTO updateCategory(Long id, String cat){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction With ID: "+id+" Was Not Found"));
        t.setCategory(cat);
        repo.save(t);
        return mapToResponseDTO(t);
    }
    public TransactionResponseDTO updateAmount(Long id, BigDecimal amnt){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Was Not Found"));
        t.setAmount(amnt);
        repo.save(t);
        return mapToResponseDTO(t);
    }
    public TransactionResponseDTO updateDate(Long id, LocalDate d){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Was Not Found"));
        t.setDate(d);
        repo.save(t);
        return mapToResponseDTO(t);
    }
    public TransactionResponseDTO updateType(Long id, TransactionType ty){
        Transaction t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction By ID: "+id+" Was Not Found"));
        t.setType(ty);
        repo.save(t);
        return mapToResponseDTO(t);
    }
}