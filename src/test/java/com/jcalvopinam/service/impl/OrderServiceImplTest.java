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

import com.jcalvopinam.controller.BaseControllerTest;
import com.jcalvopinam.domain.Order;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.OrderRepository;
import com.jcalvopinam.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.List;

import static com.jcalvopinam.utils.DummyOrder.getOptionalOrder;
import static com.jcalvopinam.utils.DummyOrder.getOrderDTO;
import static com.jcalvopinam.utils.DummyOrder.getOrders;
import static com.jcalvopinam.utils.DummyPerson.getOptionalPerson;

/**
 * @author Juan Calvopina
 */
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OrderServiceImplTest extends BaseControllerTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void findAll() {
        Mockito.when(orderRepository.findAll())
               .thenReturn(getOrders());
        final List<Order> all = orderService.findAll();
        Assertions.assertEquals(getOrders().size(), all.size(), "It is missing products");
    }

    @Test
    void findByText() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 25, 0, 0, 0);

        Mockito.when(
                       orderRepository.findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(1L, null, null,
                                                                                                      null, 0))
               .thenReturn(getOrders());
        final List<Order> byText = orderService.findByText("1", null, null, null, "0");
        Assertions.assertEquals(0, byText.size(), "The id is null");
    }

    @Test
    void findByTextNumberFormatException() {
        Mockito.when(
                       orderRepository.findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(1L, 0L, 0L, null, 0))
               .thenReturn(null);

        Assertions.assertThrows(NumberFormatException.class, () -> orderService.findByText("1", "0L", "0L", null, "0"),
                                "The Order 1 not found");
    }

    @Test
    void save() {
        final Order order = getOrders().get(0);

        Mockito.when(personRepository.findById(1L))
               .thenReturn(getOptionalPerson());

        Mockito.when(orderRepository.save(Mockito.any()))
               .thenReturn(order);

        final Order save = orderService.save(getOrderDTO());
        Assertions.assertNotNull(save.getOrderId(), "The id is null");
    }

    @Test
    void update() {
        final Order order = getOrders().get(0);

        Mockito.when(personRepository.findById(1L))
               .thenReturn(getOptionalPerson());

        Mockito.when(orderRepository.findById(Mockito.any()))
               .thenReturn(getOptionalOrder());

        Mockito.when(orderRepository.save(Mockito.any()))
               .thenReturn(order);

        final Order updated = orderService.update(1L, getOrderDTO());
        Assertions.assertNotNull(updated.getOrderId(), "The id is null");
    }

    @Test
    void updateNotFound() {
        Mockito.when(personRepository.findById(0L))
               .thenReturn(getOptionalPerson());

        Assertions.assertThrows(NotFoundException.class, () -> orderService.update(0L, getOrderDTO()),
                                "Expected NotFoundException");
    }

    @Test
    void deleteById() {
        Mockito.when(orderRepository.findById(Mockito.any()))
               .thenReturn(getOptionalOrder());

        Mockito.doNothing()
               .when(orderRepository)
               .delete(Mockito.any());

        orderService.deleteById(1L);
        Mockito.verify(orderRepository, Mockito.times(1))
               .delete(Mockito.any());
    }

}