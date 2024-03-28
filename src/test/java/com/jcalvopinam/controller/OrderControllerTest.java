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

import static com.jcalvopinam.utils.DummyOrder.getOrderDTO;
import static com.jcalvopinam.utils.DummyPerson.getPersonDTO;

/**
 * @author Juan Calvopina
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/orders";
    private static final String ORDER_ID = "/1";


    @Test
    @Order(3)
    void findAllOrders() throws Exception {
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
    void findByTest() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL.concat(ORDER_ID))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @Order(2)
    void saveOrder() throws Exception {
        final MockHttpServletResponse response =
                createObject(PersonControllerTest.BASE_URL, getPersonDTO());
        createObject(BASE_URL, getOrderDTO());
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    @Order(1)
    void updateOrder_notFound() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL.concat(ORDER_ID))
                                                      .content(asJsonString(getOrderDTO()))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @Order(5)
    void updateOrder() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL.concat(ORDER_ID))
                                                      .content(asJsonString(getOrderDTO()))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @Order(6)
    void deleteOrder() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL.concat(ORDER_ID))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    private MockHttpServletResponse createObject(final String baseURL, final Object objectToCreate) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(baseURL)
                                                     .content(asJsonString(objectToCreate))
                                                     .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(MockMvcResultMatchers.content()
                                                      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                      .andReturn()
                      .getResponse();
    }

}