package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> getCustomerByEmail(String email);
    Optional<Customer> findOneByEmail(String email);
}
