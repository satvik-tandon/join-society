package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.Cart;
import edu.dbms.joinsociety.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> getCartByCustomer(Customer customer);

    Cart getOneByCustomer(Customer customer);
}
