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
import com.jcalvopinam.dto.ProductRequestDTO;
import com.jcalvopinam.dto.ProductResponseDTO;
import com.jcalvopinam.exception.ConflictException;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.ProductRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author juan.calvopina
 */
@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    public ProductServiceImpl(ProductRepository productRepository,
                              final ConversionService conversionService) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
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
    public List<ProductResponseDTO> findByText(String id, String name) {
        Integer productId = Utilities.isInteger(id);
        final List<Product> productSourceList = productRepository.findByProductIdOrNameStartingWith(productId, name);

        if (productSourceList.isEmpty()) {
            final String message = "There was no data found";
            log.info(message);
            throw new NotFoundException(message);
        }

        final TypeDescriptor productSourceType =
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Product.class));

        final TypeDescriptor productTargetType =
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ProductResponseDTO.class));

        return (List<ProductResponseDTO>) conversionService.convert(productSourceList, productSourceType,
                                                                    productTargetType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductResponseDTO save(ProductRequestDTO productRequestDTO) {
        Validate.notNull(productRequestDTO, "The product cannot be null");

        productRepository.findByName(productRequestDTO.getName())
                         .ifPresent(prod -> {
                             final String message = String.format("The product %s already exist in the database",
                                                                  prod.getName());
                             log.info(message);
                             throw new ConflictException(message);
                         });

        final Product dtoToProduct = conversionService.convert(productRequestDTO, Product.class);
        final Product product = productRepository.save(Objects.requireNonNull(dtoToProduct));
        log.info("Product saved!");
        return conversionService.convert(product, ProductResponseDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductResponseDTO update(final int id, final ProductRequestDTO productRequestDTO) {
        Validate.notNull(productRequestDTO, "The product cannot be null");
        Product existingProduct = findProductById(id);
        final Product productConverted = conversionService.convert(productRequestDTO, existingProduct.getClass());
        productConverted.setProductId(id);
        final Product saved = productRepository.save(Objects.requireNonNull(productConverted));
        log.info("Product updated!");
        return conversionService.convert(saved, ProductResponseDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(int id) {
        final Product product = findProductById(id);
        productRepository.delete(product);
        log.info("Product deleted!");
    }

    /**
     * Finds the product by {@code id}.
     *
     * @param id receive an id.
     * @return a Product object.
     */
    private Product findProductById(final int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> {
                                    final String message = "Product not found!";
                                    log.error(message);
                                    return new NotFoundException(message);
                                });
    }

}
