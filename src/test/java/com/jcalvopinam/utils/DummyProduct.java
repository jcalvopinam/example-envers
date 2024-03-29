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

package com.jcalvopinam.utils;

import com.jcalvopinam.domain.Product;
import com.jcalvopinam.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Juan Calvopina
 */
public final class DummyProduct {

    private DummyProduct() {
    }

    public static Optional<Product> getOptionalProduct() {
        return getProducts()
                .stream()
                .findFirst();
    }

    public static Product getProduct() {
        final Optional<Product> product = getOptionalProduct();
        return product.orElseGet(Product::new);
    }

    public static List<Product> getProducts() {
        return IntStream.range(1, 6)
                        .mapToObj(i -> Product.builder()
                                              .productId((long) i)
                                              .name("NAME " + i)
                                              .description("DESCRIPTION " + i)
                                              .quantityPerUnit(1)
                                              .unitPrice(9.99 - i)
                                              .build())
                        .collect(Collectors.toCollection(() -> new ArrayList<>(5)));
    }

    public static ProductDTO getProductDTO() {
        final Product product = getProducts().get(0);
        return new ProductDTO(product.getProductId(), product.getName(), product.getDescription(),
                              product.getQuantityPerUnit(), product.getUnitPrice());
    }

}
