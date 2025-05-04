package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
