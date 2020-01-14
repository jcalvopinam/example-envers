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

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.dto.OrderDetailDTO;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface OrderDetailService {

    /**
     * Retrieves all order details from database
     *
     * @return a List of OrderDetail.
     */
    List<OrderDetail> findAll();

    /**
     * Finds the order detail by id or customer, employee, date or orderStatus
     *
     * @return an OrderDetail object.
     */
    OrderDetail findByDetailPk(String productId, String orderId);

    /**
     * Adds a new order detail to the database
     *
     * @return an OrderDetail object.
     */
    OrderDetail save(OrderDetailDTO orderDetailDTO);

    /**
     * Updates a order detail to the database
     *
     * @param orderDetailDTO receive an OrderDetailDTO object.
     * @param id             receive an id value.
     * @return an OrderDetail object.
     */
    OrderDetail update(OrderDetailDTO orderDetailDTO, int id);

    /**
     * Deletes a order detail by orderDetailPK from database
     *
     * @param id receive an id value.
     */
    void deleteById(int id);

}
