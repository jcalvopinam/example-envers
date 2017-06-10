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

/**
 * Methods for accessing the database
 */
package com.jcalvopinam.repository;

import com.jcalvopinam.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * @param orderId
     * @param customer
     * @param employee
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(int orderId,
                                                                               int customer,
                                                                               int employee,
                                                                               Date date,
                                                                               int orderStatus);

    /**
     * @param orderId
     * @param customerName
     * @param customerLastName
     * @param employee
     * @param employeeLastName
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(
            int orderId,
            String customerName,
            String customerLastName,
            String employee,
            String employeeLastName,
            Date date,
            int orderStatus);

}
