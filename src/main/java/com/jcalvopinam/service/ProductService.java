/*
 * MIT License
 *
 * Copyright (c) 2017 JUAN CALVOPINA M
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
 * @author juan.calvopina
 */
public interface ProductService {

    /**
     * Retrieves all products from database.
     *
     * @return a List of product.
     */
    List<Product> findAll();

    /**
     * Finds the product by id or name.
     *
     * @param id   receive an id value.
     * @param name receive a name value.
     * @return a ProductDTO object.
     */
    ProductDTO findByText(String id, String name);

    /**
     * Adds a new product to the database.
     *
     * @param productDTO receive an ProductDTO object.
     * @return a ProductDTO object.
     */
    ProductDTO save(ProductDTO productDTO);

    /**
     * Updates a product to the database
     *
     * @param productDTO receive an ProductDTO object.
     * @return a ProductDTO object.
     */
    ProductDTO update(ProductDTO productDTO);

    /**
     * Deletes a product by Id from database
     *
     * @param id receive an id.
     */
    void deleteById(int id);

}
