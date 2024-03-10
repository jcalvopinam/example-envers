/*
 * MIT License
 *
 * Copyright (c) 2022 JUAN CALVOPINA M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.jcalvopinam.service;

import com.jcalvopinam.domain.Product;
import com.jcalvopinam.dto.ProductDTO;

import java.util.List;

/**
 * @author Juan Calvopina
 */
public interface ProductService {

    /**
     * Retrieves all products from database.
     *
     * @return a List of products.
     */
    List<Product> findAll();

    /**
     * Finds the product by id or name.
     *
     * @param id   receives an id value.
     * @param name receives a name value.
     *
     * @return the ProductResponseDTO object.
     */
    List<Product> findByText(String id, String name);

    /**
     * Finds the product by id.
     *
     * @param id receives the id to be filtered.
     *
     * @return the Project object.
     */
    Product findById(Long id);

    /**
     * Adds the new product to the database.
     *
     * @param productDTO receives an ProductDTO object.
     *
     * @return the ProductDTO object.
     */
    Product save(ProductDTO productDTO);

    /**
     * Updates the product to the database
     *
     * @param productDTO receives an ProductDTO object.
     * @param id         receives an id.
     *
     * @return a ProductDTO object.
     */
    Product update(ProductDTO productDTO, Long id);

    /**
     * Deletes a product by id from database
     *
     * @param id receives an id.
     */
    void deleteById(Long id);

}
