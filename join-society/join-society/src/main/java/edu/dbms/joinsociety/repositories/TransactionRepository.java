package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Optional<Transaction> findOneByOrderId(String orderId);
}
