package com.finance.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finance.tracker.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findByAmountGreaterThan(Double amnt);

    @Query(value = "SELECT category FROM transaction WHERE type = 'EXPENSE' GROUP BY category ORDER BY SUM(amount) DESC LIMIT 1", nativeQuery = true)
    String findMostCostlyCategory();
}