package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.Product;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findOneById(Long id);
    List<Product> findByGenderId(Integer genderId);
    List<Product> findByGenderIdOrderByUpdatedAtDesc(Integer genderId, Limit limit);
}
