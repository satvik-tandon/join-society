package edu.dbms.joinsociety.repositories;

import edu.dbms.joinsociety.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
