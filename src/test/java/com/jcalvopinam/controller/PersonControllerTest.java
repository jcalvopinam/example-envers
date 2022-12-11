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
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.jcalvopinam.utils.DummyPerson.getPersonDTO;

/**
 * @author Juan Calvopina <juan.calvopina@gmail.com>
 */
class PersonControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/person";
    private static final String PERSON_ID = "/1";

    @Test
    void findAllPeople() throws Exception {
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
    void findByText() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL.concat(PERSON_ID))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void savePerson() throws Exception {
        final MockHttpServletResponse response = createPerson();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void updatePerson() throws Exception {
        final MockHttpServletResponse product = createPerson();
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL.concat(PERSON_ID))
                                                      .content(asJsonString(getPersonDTO()))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.content()
                                                       .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();

        Assertions.assertEquals(HttpStatus.CREATED.value(), product.getStatus());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deletePerson() throws Exception {
        final MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL.concat(PERSON_ID))
                                                      .contentType(MediaType.APPLICATION_JSON))
                       .andReturn()
                       .getResponse();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    private MockHttpServletResponse createPerson() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                                                     .content(asJsonString(getPersonDTO()))
                                                     .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(MockMvcResultMatchers.content()
                                                      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                      .andReturn()
                      .getResponse();
    }

}