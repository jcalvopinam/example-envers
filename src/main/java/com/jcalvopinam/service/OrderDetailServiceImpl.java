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
import com.jcalvopinam.exception.OrderDetailNotFoundException;
import com.jcalvopinam.repository.OrderDetailRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author juan.calvopina
 */
@Service
@Transactional
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ConversionService conversionService;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository,
                                  final ConversionService conversionService) {
        this.orderDetailRepository = orderDetailRepository;
        this.conversionService = conversionService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetail findByDetailPk(String productId, String orderId) {
        Integer prodId = Utilities.isInteger(productId);
        Integer ordId = Utilities.isInteger(orderId);
        return orderDetailRepository.findById(new OrderDetailPK(prodId, ordId))
                                    .orElseThrow(() -> {
                                        final String message = "Order detail not found!";
                                        log.error(message);
                                        return new OrderDetailNotFoundException(message);
                                    });
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public OrderDetailDTO save(final OrderDetailDTO orderDetailDTO) {
        final OrderDetail orderDetail = orderDetailRepository.save(new OrderDetail(orderDetailDTO));
        log.info("Order Detail saved!");
        return conversionService.convert(orderDetail, OrderDetailDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailDTO update(final OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = findOrderDetail(orderDetailDTO);
        final OrderDetail OrderDetailConverted = conversionService.convert(orderDetailDTO, orderDetail.getClass());
        final OrderDetail updated = orderDetailRepository.save(Objects.requireNonNull(OrderDetailConverted));
        log.info("Order Detail updated!");
        return conversionService.convert(updated, OrderDetailDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(final OrderDetailDTO orderDetailDTO) {
        final OrderDetail orderDetail = findOrderDetail(orderDetailDTO);
        orderDetailRepository.delete(orderDetail);
        log.info("Order Detail deleted!");
    }

    private OrderDetail findOrderDetail(final OrderDetailDTO orderDetailDTO) {
        return orderDetailRepository.findById(orderDetailDTO.getId())
                                    .orElseThrow(() -> {
                                        final String message = "Order detail not found!";
                                        log.error(message);
                                        return new OrderDetailNotFoundException(message);
                                    });
    }

}
