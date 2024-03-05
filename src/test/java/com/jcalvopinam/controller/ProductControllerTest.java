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

package com.jcalvopinam.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.jcalvopinam.utils.DummyProduct.getProduct;
import static com.jcalvopinam.utils.DummyProduct.getProductDTO;

/**
 * @author Juan Calvopina <juan.calvopina@gmail.com>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/products";
    private static final String PRODUCT_ID = "/1";

    @Test
    @Order(3)
    void findAllProducts() throws Exception {

        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @Order(4)
    void findByText() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL.concat(PRODUCT_ID))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @Order(2)
    void saveProduct() throws Exception {
        final MockHttpServletResponse response = createProduct();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    @Order(6)
    void saveProduct_conflicted() throws Exception {
        createProduct();
        final MockHttpServletResponse responseConflicted = createProduct();
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), responseConflicted.getStatus());
    }

    @Test
    @Order(1)
    void updateProduct_notFound() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL.concat(PRODUCT_ID))
                                                      .content(asJsonString(getProduct()))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @Order(5)
    void updateProduct() throws Exception {
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL.concat(PRODUCT_ID))
                                                      .content(asJsonString(getProductDTO()))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @Order(7)
    void deleteProduct() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL.concat(PRODUCT_ID))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    private MockHttpServletResponse createProduct() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                                                     .content(asJsonString(getProductDTO()))
                                                     .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(MockMvcResultMatchers.content()
                                                      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                      .andReturn()
                      .getResponse();
    }

}