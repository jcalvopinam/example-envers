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
import com.jcalvopinam.exception.OrderException;
import com.jcalvopinam.repository.OrderRepository;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author juan.calvopina
 */
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    private String response;

    public OrderServiceImpl(OrderRepository orderRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
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
        Integer orderId = Utilities.isInteger(id);
        Integer customerId = Utilities.isInteger(customer);
        Integer employeeId = Utilities.isInteger(employee);
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
    public String save(OrderDTO orderDTO) {
        response = "Order saved!";
        Person customer = findPerson(orderDTO.getCustomer());
        Person employee = findPerson(orderDTO.getEmployee());
        orderRepository.save(new Order(orderDTO, customer, employee));
        log.info(response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String update(OrderDTO orderDTO) {
        response = "Person updated!";
        Person employee = findPerson(orderDTO.getEmployee());
        Person customer = findPerson(orderDTO.getCustomer());
        Order order = orderRepository.findById(orderDTO.getId())
                                     .orElseThrow(() -> {
                                         final String message = "Order detail not found!";
                                         log.error(message);
                                         throw new OrderException(message);
                                     });
        order = this.updateOrder(order, orderDTO, employee, customer);
        orderRepository.save(order);
        log.info(response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteById(int id) {
        response = "Order deleted!";
        orderRepository.deleteById(id);
        log.info(response);
        return response;
    }

    private Order updateOrder(Order order, OrderDTO orderDTO, Person employee, Person customer) {
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setSaleDate(Utilities.matchDate(orderDTO.getSaleDate()));
        return order;
    }

    private Person findPerson(final int customer2) {
        return personRepository.findById(customer2)
                               .orElseThrow(() -> {
                                   final String message = "Order detail not found!";
                                   log.error(message);
                                   throw new OrderException(message);
                               });
    }

}
