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

package com.jcalvopinam.repository;

import com.jcalvopinam.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Juan Calvopina
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * Find by OrderId or CustomerId or EmployeeId or SaleDate or OrderStatus.
     *
     * @param orderId     receives an orderId value.
     * @param customerId  receives a customerId value.
     * @param employeeId  receives an employeeId value.
     * @param date        receives a date value.
     * @param orderStatus receives an orderStatus value.
     *
     * @return a List of Orders.
     */
    List<Order> findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(Long orderId, Long customerId,
                                                                               Long employeeId, Date date,
                                                                               int orderStatus);

    /**
     * Find by OrderId or Customer (FirstName or LastName) or Employee (FirstName or LastName) or SaleDate or OrderStatus.
     *
     * @param orderId          receives an orderId value.
     * @param customerName     receives a customerName value.
     * @param customerLastName receives a customerLastName value.
     * @param employee         receives an employee value.
     * @param employeeLastName receives an employeeLastName value.
     * @param date             receives a date value.
     * @param orderStatus      receives an orderId value.
     *
     * @return a List of Orders.
     */
    List<Order> findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(
            Long orderId, String customerName, String customerLastName, String employee, String employeeLastName,
            Date date, int orderStatus);

}
