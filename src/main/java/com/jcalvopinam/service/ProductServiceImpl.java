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
import com.jcalvopinam.exception.PersonNotFoundException;
import com.jcalvopinam.repository.ProductRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
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
    public ProductDTO findByText(String id, String name) {
        Integer productId = Utilities.isInteger(id);
        final Product product = productRepository.findByProductIdOrName(productId, name);
        return conversionService.convert(product, ProductDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        final Product product = productRepository.save(new Product(productDTO));
        log.info("Product saved!");
        return conversionService.convert(product, ProductDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDTO update(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                                           .orElseThrow(() -> {
                                               final String message = "Order detail not found!";
                                               log.error(message);
                                               return new PersonNotFoundException(message);
                                           });
        final Product productConverted = conversionService.convert(productDTO, product.getClass());
        final Product saved = productRepository.save(Objects.requireNonNull(productConverted));
        log.info("Product updated!");
        return conversionService.convert(saved, ProductDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
        log.info("Product deleted!");
    }

}
