package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist getWishlistByCustomer(Customer customer);
}
