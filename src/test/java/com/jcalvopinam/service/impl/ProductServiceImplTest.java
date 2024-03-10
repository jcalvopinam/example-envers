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

package com.jcalvopinam.service.impl;

import com.jcalvopinam.controller.BaseControllerTest;
import com.jcalvopinam.domain.Product;
import com.jcalvopinam.dto.ProductDTO;
import com.jcalvopinam.exception.AlreadyExistsException;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.jcalvopinam.utils.DummyProduct.getOptionalProduct;
import static com.jcalvopinam.utils.DummyProduct.getProduct;
import static com.jcalvopinam.utils.DummyProduct.getProductDTO;
import static com.jcalvopinam.utils.DummyProduct.getProducts;

/**
 * @author Juan Calvopina
 */
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest extends BaseControllerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findAll() {
        Mockito.when(productRepository.findAll())
               .thenReturn(getProducts());
        final List<Product> all = this.productService.findAll();
        Assertions.assertEquals(getProducts().size(), all.size(), "It is missing products");
    }

    @Test
    void findByText_id() {
        Mockito.when(productRepository.findByProductIdOrNameContainingIgnoreCase(1L, null))
               .thenReturn(getProducts());
        final List<Product> byText = this.productService.findByText("1", null);
        Assertions.assertNotNull(byText.get(0)
                                       .getProductId(), "The id is null");
    }

    @Test
    void findByText_name() {
        Mockito.when(productRepository.findByProductIdOrNameContainingIgnoreCase(1L, "Something"))
               .thenReturn(getProducts());
        final List<Product> byText = this.productService.findByText("1", "Something");
        Assertions.assertNotNull(byText.get(0)
                                       .getName(), "The name is null");
    }

    @Test
    void findById() {
        Mockito.when(productRepository.findById(1L))
               .thenReturn(getOptionalProduct());
        final Product productFound = this.productService.findById(1L);
        Assertions.assertEquals(1L, productFound.getProductId(), "The is is null");
    }

    @Test
    void findById_NotFoundException() {
        Mockito.when(productRepository.findById(0L))
               .thenReturn(getOptionalProduct());

        Assertions.assertThrows(NotFoundException.class, () -> productService.findById(1L), "The Product 1 not found");
    }

    @Test
    void save() {
        final ProductDTO productDTO = getProductDTO();
        productDTO.setProductId(null);

        Mockito.when(productRepository.findById(productDTO.getProductId()))
               .thenReturn(Optional.empty());

        final Product product = new Product(productDTO);

        Mockito.when(productRepository.save(Mockito.any()))
               .thenReturn(product);

        final Product productSaved = productService.save(productDTO);
        Assertions.assertNotNull(productSaved.getName(), "The id is null");
    }

    @Test
    void save_alreadyExistsException() {
        final ProductDTO productDTO = getProductDTO();

        Mockito.when(productRepository.findById(productDTO.getProductId()))
               .thenReturn(getOptionalProduct());

        final Product product = getProduct();

        Mockito.when(productRepository.save(Mockito.any()))
               .thenReturn(product);

        Assertions.assertThrows(AlreadyExistsException.class, () -> productService.save(productDTO),
                                "Expected AlreadyExistsException");
    }

    @Test
    void update() {
        final ProductDTO productDTO = getProductDTO();

        Mockito.when(productRepository.findById(1L))
               .thenReturn(getOptionalProduct());

        if (getOptionalProduct().isEmpty()) {
            Assertions.fail();
        }

        final Product product = getOptionalProduct().get();

        Mockito.when(productRepository.save(Mockito.any()))
               .thenReturn(product);

        final Product productSaved = productService.update(productDTO, 1L);
        Assertions.assertNotNull(productSaved.getName(), "The id is null");
    }

    @Test
    void deleteById() {
        Mockito.when(productRepository.findById(1L))
               .thenReturn(getOptionalProduct());

        if (getOptionalProduct().isEmpty()) {
            Assertions.fail();
        }

        final Product product = getOptionalProduct().get();

        productService.deleteById(product.getProductId());

        Assertions.assertNotNull(product.getProductId());

        Mockito.verify(productRepository, Mockito.times(1))
               .deleteById(product.getProductId());
    }

}