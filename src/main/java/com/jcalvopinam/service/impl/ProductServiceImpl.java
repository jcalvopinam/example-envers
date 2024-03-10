/*
 * MIT License
 *
 * Copyright (c) 2024 JUAN CALVOPINA M
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

package com.jcalvopinam.service.impl;

import com.jcalvopinam.domain.Product;
import com.jcalvopinam.dto.ProductDTO;
import com.jcalvopinam.exception.AlreadyExistsException;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.ProductRepository;
import com.jcalvopinam.service.ProductService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Juan Calvopina
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> findAll() {
        // TODO: fix findAll anti-pattern
        LOGGER.info("Getting the products");
        return (List<Product>) productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> findByText(final String id, final String name) {
        LOGGER.info("Finding by {} or {}", id, name);
        Long productId = 0L;
        if (NumberUtils.isCreatable(id)) {
            productId = NumberUtils.createLong(id);
        }
        final List<Product> byProductIdOrName = productRepository.findByProductIdOrNameContainingIgnoreCase(productId,
                                                                                                            name);
        if (byProductIdOrName.isEmpty()) {
            throw new NotFoundException(String.format("The Product %s not found", id));
        }
        return byProductIdOrName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product findById(final Long id) {
        LOGGER.info("Finding by {}", id);
        return productRepository.findById(id)
                                .orElseThrow(
                                        () -> new NotFoundException(String.format("The Product %s not found", id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product save(final ProductDTO productDTO) {
        if (productRepository.findById(productDTO.getProductId())
                             .isPresent()) {
            final String message = String.format("The Product %s already exist", productDTO.getProductId());
            LOGGER.error(message);
            throw new AlreadyExistsException(message);
        }
        LOGGER.info("Saving new product {}", productDTO.getName());
        final Product entity = new Product(productDTO);
        return productRepository.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product update(final ProductDTO productDTO, final Long id) {
        final Product productFound = this.findById(id);
        Product product = this.updateProduct(productFound, productDTO);
        LOGGER.info("Updating the product {}", productDTO.getName());
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(final Long id) {
        final Product product = this.findById(id);
        LOGGER.info("Deleting the product {}", product.getProductId());
        productRepository.deleteById(product.getProductId());
    }

    /**
     * Mapping the attributes from DTO object to the Pojo.
     *
     * @param product    receives the product object.
     * @param productDTO receives the productDTO object.
     *
     * @return the Product object.
     */
    private Product updateProduct(final Product product, final ProductDTO productDTO) {
        LOGGER.info("Mapping the product {}", productDTO.getName());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantityPerUnit(productDTO.getQuantityPerUnit());
        product.setUnitPrice(productDTO.getUnitPrice());
        return product;
    }

}
