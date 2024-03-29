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

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.domain.OrderDetailPK;
import com.jcalvopinam.dto.OrderDetailDTO;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.OrderDetailRepository;
import com.jcalvopinam.service.OrderDetailService;
import com.jcalvopinam.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Juan Calvopina
 */
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAll() {
        LOGGER.info("Getting the order details");
        return (List<OrderDetail>)orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findByDetailPk(String productId, String orderId) {
        LOGGER.info("Finding by {} or {}", productId, orderId);
        long prodId = Utilities.getLongValue(productId);
        long ordId = Utilities.getLongValue(orderId);
        return orderDetailRepository.findById(new OrderDetailPK(prodId, ordId))
                                    .orElseThrow(() -> new NotFoundException("Order Detail not found"));
    }

    @Override
    public OrderDetail save(OrderDetailDTO orderDetailDTO) {
        LOGGER.info("Saving the order detail {}", orderDetailDTO.getId());
        return orderDetailRepository.save(new OrderDetail(orderDetailDTO));
    }

    @Override
    public OrderDetail update(OrderDetailDTO orderDetailDTO) {
        LOGGER.info("Updating the order detail {}", orderDetailDTO.getId());
        final OrderDetail orderDetail =
                orderDetailRepository.findById(orderDetailDTO.getId())
                                     .orElseThrow(() -> new NotFoundException("Order Detail not found"));
        final OrderDetail orderDetailUpdated = this.updateOrderDetail(orderDetail, orderDetailDTO);
        return orderDetailRepository.save(orderDetailUpdated);
    }

    @Override
    public void deleteById(OrderDetailPK orderDetailPK) {
        LOGGER.info("Deleting the order detail {}", orderDetailPK.getOrderId());
        orderDetailRepository.deleteById(orderDetailPK);
    }

    private OrderDetail updateOrderDetail(OrderDetail orderDetail,
                                          OrderDetailDTO orderDetailDTO) {
        orderDetail.setUnitPrice(orderDetailDTO.getUnitPrice());
        orderDetail.setDiscount(orderDetailDTO.getDiscount());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        return orderDetail;
    }

}
