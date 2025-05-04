package edu.dbms.joinsociety.service.product;


import edu.dbms.joinsociety.dto.SizeDTO;

import java.util.List;

public interface ProductCategoryService {
    List<SizeDTO> getSizesAssociatedWithProductCategory(Integer categoryId);
}
