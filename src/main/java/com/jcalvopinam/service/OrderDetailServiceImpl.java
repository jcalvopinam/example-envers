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
import com.jcalvopinam.domain.OrderDetailPK;
import com.jcalvopinam.dto.OrderDetailDTO;
import com.jcalvopinam.repository.OrderDetailRepository;
import com.jcalvopinam.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderDetailRepository orderDetailRepository;

    private String response;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findByDetailPk(String productId, String orderId) {
        Integer prodId = Utilities.isInteger(productId);
        Integer ordId = Utilities.isInteger(orderId);
        return orderDetailRepository.findById(new OrderDetailPK(prodId, ordId));
    }

    @Override
    public String save(OrderDetailDTO orderDetailDTO) {
        response = "Order Detail saved!";
        orderDetailRepository.save(new OrderDetail(orderDetailDTO));
        logger.info(response);
        return response;
    }

    @Override
    public String update(OrderDetailDTO orderDetailDTO) {
        response = "Order Detail updated!";
        OrderDetail orderDetail = orderDetailRepository.findOne(orderDetailDTO.getId());
        orderDetail = this.updateOrderDetail(orderDetail, orderDetailDTO);
        orderDetailRepository.save(orderDetail);
        logger.info(response);
        return response;
    }

    @Override
    public String deleteById(OrderDetailPK orderDetailPK) {
        response = "Order Detail deleted!";
        orderDetailRepository.delete(orderDetailPK);
        return response;
    }

    private OrderDetail updateOrderDetail(OrderDetail orderDetail,
                                          OrderDetailDTO orderDetailDTO) {
        orderDetail.setUnitPrice(orderDetailDTO.getUnitPrice());
        orderDetail.setDiscount(orderDetailDTO.getDiscount());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        return orderDetail;
    }

}
