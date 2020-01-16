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

package com.jcalvopinam.repository;

import com.jcalvopinam.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Methods for accessing the database
 *
 * @author juan.calvopina
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * Find by OrderId or CustomerId or EmployeeId or SaleDate or OrderStatus.
     *
     * @param orderId     receive an orderId value.
     * @param customer    receive a customer value.
     * @param employee    receive an employee value.
     * @param date        receive a date value.
     * @param orderStatus receive an orderStatus value.
     * @return a List of Orders.
     */
    List<Order> findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(int orderId, int customer, int employee,
                                                                               Date date, int orderStatus);

    /**
     * Find by OrderId or Customer (FirstName or LastName) or Employee (FirstName or LastName) or SaleDate or OrderStatus.
     *
     * @param orderId          receive an orderId value.
     * @param customerName     receive a customerName value.
     * @param customerLastName receive a customerLastName value.
     * @param employee         receive an employee value.
     * @param employeeLastName receive an employeeLastName value.
     * @param date             receive a date value.
     * @param orderStatus      receive an orderId value.
     * @return a List of Orders.
     */
    List<Order> findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(
            int orderId, String customerName, String customerLastName, String employee, String employeeLastName,
            Date date, int orderStatus);

}
