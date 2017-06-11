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
 * List of methods signature
 */
package com.jcalvopinam.service;

import com.jcalvopinam.domain.Order;
import com.jcalvopinam.dto.OrderDTO;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface OrderService {

    /**
     * Retrieves all orders from database
     *
     * @return
     */
    List<Order> findAll();

    /**
     * Finds the order by id or customer, employee, date or orderStatus
     *
     * @param id
     * @param customer
     * @param employee
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByText(String id, String customer, String employee, String date, String orderStatus);

    /**
     * Adds a new order to the database
     *
     * @param orderDTO
     * @return
     */
    String save(OrderDTO orderDTO);

    /**
     * Updates a order to the database
     *
     * @param orderDTO
     * @return
     */
    String update(OrderDTO orderDTO);

    /**
     * Deletes a order by Id from database
     *
     * @param id
     * @return
     */
    String deleteById(int id);

}
