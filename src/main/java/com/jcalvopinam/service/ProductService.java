package com.jcalvopinam.service;

import com.jcalvopinam.domain.Product;
import com.jcalvopinam.dto.ProductDTO;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface ProductService {

    /**
     * Retrieves all products from database
     *
     * @return
     */
    List<Product> findAll();

    /**
     * Finds the product by id or name
     *
     * @param id
     * @return
     */
    Product findByText(String id, String name);

    /**
     * Adds a new product to the database
     *
     * @param productDTO
     * @return
     */
    String save(ProductDTO productDTO);

    /**
     * Updates a product to the database
     *
     * @param productDTO
     * @return
     */
    String update(ProductDTO productDTO);

    /**
     * Deletes a product by Id from database
     *
     * @param id
     * @return
     */
    String deleteById(int id);

}
