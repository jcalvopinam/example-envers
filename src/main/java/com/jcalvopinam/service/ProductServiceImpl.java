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
import com.jcalvopinam.exception.PersonException;
import com.jcalvopinam.repository.ProductRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author juan.calvopina
 */
@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private String response;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product findByText(String id, String name) {
        Integer productId = Utilities.isInteger(id);
        return productRepository.findByProductIdOrName(productId, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String save(ProductDTO productDTO) {
        response = "Product saved!";
        productRepository.save(new Product(productDTO));
        log.info(response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String update(ProductDTO productDTO) {
        response = "Product updated!";
        Product product = productRepository.findById(productDTO.getId())
                                           .orElseThrow(() -> {
                                               final String message = "Order detail not found!";
                                               log.error(message);
                                               throw new PersonException(message);
                                           });
        product = this.updateProduct(product, productDTO);
        productRepository.save(product);
        log.info(response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteById(int id) {
        response = "Product deleted!";
        productRepository.deleteById(id);
        log.info(response);
        return response;
    }

    private Product updateProduct(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantityPerUnit(productDTO.getQuantityPerUnit());
        product.setUnitPrice(productDTO.getUnitPrice());
        return product;
    }

}
