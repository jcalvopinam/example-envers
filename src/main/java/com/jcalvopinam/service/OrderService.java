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

package com.jcalvopinam.service;

import com.jcalvopinam.domain.Order;
import com.jcalvopinam.dto.OrderDTO;

import java.util.List;

/**
 * @author Juan Calvopina <juan.calvopina@gmail.com>
 */
public interface OrderService {

    /**
     * Retrieves all orders from database.
     *
     * @return List of Order.
     */
    List<Order> findAll();

    /**
     * Finds the order by id or customer, employee, date or orderStatus.
     *
     * @param id          receives an id value.
     * @param customer    receives a customer value.
     * @param employee    receives a employee value
     * @param date        receives a date value.
     * @param orderStatus receives an orderStatus value.
     *
     * @return List of Order.
     */
    List<Order> findByText(String id, String customer, String employee, String date, String orderStatus);

    /**
     * Adds a new order to the database.
     *
     * @param orderDTO receives an OrderDTO object.
     *
     * @return Order object.
     */
    Order save(OrderDTO orderDTO);

    /**
     * Updates an order to the database.
     *
     * @param id       receives an Id.
     * @param orderDTO receives an OrderDTO object.
     *
     * @return Order object.
     */
    Order update(Long id, OrderDTO orderDTO);

    /**
     * Deletes an order by Id from database.
     *
     * @param id receives an id.
     */
    void deleteById(Long id);

}
