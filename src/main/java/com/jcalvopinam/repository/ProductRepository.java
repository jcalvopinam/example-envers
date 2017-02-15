package com.jcalvopinam.repository;

import com.jcalvopinam.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Finds the product by id or name
     *
     * @param id
     * @param name
     * @return
     */
    Product findByProductIdOrName(int id, String name);

}
