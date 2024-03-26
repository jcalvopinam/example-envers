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

import com.jcalvopinam.domain.Order;
import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.OrderDTO;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.OrderRepository;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.service.OrderService;
import com.jcalvopinam.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Juan Calvopina
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    public OrderServiceImpl(final OrderRepository orderRepository, final PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> findByText(final String id, final String customer, final String employee, final String date,
                                  final String orderStatus) {
        LOGGER.info("Finding by {} or {} or {} or {} or {}", id, customer, employee, date, orderStatus);
        final long orderId = Utilities.getLongValue(id);
        final long customerId = Utilities.getLongValue(customer);
        final long employeeId = Utilities.getLongValue(employee);
        final int orderStatusId = Utilities.getIntValue(orderStatus);

        final Date valueDate = Utilities.matchDate(date);

        return (customerId == 0 || employeeId == 0)
               ? orderRepository
                       .findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(
                               orderId, customer, customer, employee, employee, valueDate, orderStatusId)
               : orderRepository
                       .findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(orderId, customerId, employeeId,
                                                                                       valueDate, orderStatusId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order save(final OrderDTO orderDTO) {
        final Person customer = findPerson(orderDTO.getCustomer(), "Customer not found");
        final Person employee = findPerson(orderDTO.getEmployee(), "Employee not found");
        final Order order = create(orderDTO, customer, employee);
        return orderRepository.save(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order update(final Long id, final OrderDTO orderDTO) {
        final Person employee = findPerson(orderDTO.getEmployee(), "Employee not found");
        final Person customer = findPerson(orderDTO.getCustomer(), "Customer not found");
        final Order order = findById(id);
        updateOrder(order, orderDTO, employee, customer);
        return orderRepository.save(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(final Long id) {
        final Order order = findById(id);
        orderRepository.delete(order);
        LOGGER.info("Order deleted!");
    }

    private Order findById(final Long id) {
        return orderRepository.findById(id)
                              .orElseThrow(() -> {
                                  final String message = "Order not found!";
                                  LOGGER.error(message);
                                  return new NotFoundException(message);
                              });
    }

    private void updateOrder(final Order order, final OrderDTO orderDTO, final Person employee,
                             final Person customer) {
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

    private Person findPerson(final Long person, final String message) {
        return personRepository.findById(person)
                               .orElseThrow(() -> {
                                   LOGGER.error(message);
                                   return new NotFoundException(message);
                               });
    }

}
