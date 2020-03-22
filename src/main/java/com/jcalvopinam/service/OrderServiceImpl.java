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

import com.jcalvopinam.domain.Order;
import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.OrderDTO;
import com.jcalvopinam.exception.OrderNotFoundException;
import com.jcalvopinam.repository.OrderRepository;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author juan.calvopina
 */
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;
    private final ConversionService conversionService;

    public OrderServiceImpl(final OrderRepository orderRepository, final PersonRepository personRepository,
                            final ConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> findByText(String id, String customer, String employee, String date, String orderStatus) {
        int orderId = Utilities.isInteger(id);
        int customerId = Utilities.isInteger(customer);
        int employeeId = Utilities.isInteger(employee);
        Date valueDate = Utilities.matchDate(date);

        return (customerId == 0 || employeeId == 0)
               ? orderRepository
                       .findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(
                               orderId, customer, customer, employee, employee, valueDate, orderId)
               : orderRepository
                       .findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(orderId, customerId, employeeId,
                                                                                       valueDate, orderId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Person customer = findPerson(orderDTO.getCustomer());
        Person employee = findPerson(orderDTO.getEmployee());
        final Order order = create(orderDTO, customer, employee);
        final Order saved = orderRepository.save(Objects.requireNonNull(order));
        log.info("Order saved!");
        return conversionService.convert(saved, OrderDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Person employee = findPerson(orderDTO.getEmployee());
        Person customer = findPerson(orderDTO.getCustomer());
        Order order = findById(orderDTO.getId());
        updateOrder(order, orderDTO, employee, customer);
        final Order saved = orderRepository.save(order);
        log.info("Person updated!");
        return conversionService.convert(saved, OrderDTO.class);
    }

    private Order findById(final int id) {
        return orderRepository.findById(id)
                              .orElseThrow(() -> {
                                  final String message = "Order detail not found!";
                                  log.error(message);
                                  return new OrderNotFoundException(message);
                              });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(int id) {
        Order order = findById(id);
        orderRepository.delete(order);
        log.info("Order deleted!");
    }

    private void updateOrder(Order order, OrderDTO orderDTO, Person employee, Person customer) {
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setSaleDate(Utilities.matchDate(orderDTO.getSaleDate()));
    }

    public Order create(final OrderDTO orderDTO, final Person customer, final Person employee) {
        return Order.builder()
                    .customer(customer)
                    .employee(employee)
                    .saleDate(Utilities.matchDate(orderDTO.getSaleDate()))
                    .orderStatus(orderDTO.getOrderStatus())
                    .build();
    }

    private Person findPerson(final int person) {
        return personRepository.findById(person)
                               .orElseThrow(() -> {
                                   final String message = "Order detail not found!";
                                   log.error(message);
                                   return new OrderNotFoundException(message);
                               });
    }

}
