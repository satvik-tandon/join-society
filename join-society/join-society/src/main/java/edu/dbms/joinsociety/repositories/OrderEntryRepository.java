package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
    List<OrderEntry> findByOrderId(String orderId);
}
