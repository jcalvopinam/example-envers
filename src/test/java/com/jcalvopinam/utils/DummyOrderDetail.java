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

package com.jcalvopinam.utils;

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.domain.OrderDetailPK;
import com.jcalvopinam.dto.OrderDetailDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Juan Calvopina
 */
public final class DummyOrderDetail {

    public static Optional<OrderDetail> getOptionalOrderDetail() {
        return getOrderDetails().stream()
                                .findFirst();
    }

    public static OrderDetail getOrderDetail() {
        final Optional<OrderDetail> orderDetail = getOptionalOrderDetail();
        return orderDetail.orElseGet(OrderDetail::new);
    }

    public static List<OrderDetail> getOrderDetails() {
        return IntStream.range(1, 6)
                        .mapToObj(i -> OrderDetail.builder()
                                                  .id(getOrderDetailPK(i))
                                                  .discount(0)
                                                  .quantity(1)
                                                  .unitPrice(100)
                                                  .build())
                        .collect(Collectors.toCollection(() -> new ArrayList<>(5)));
    }

    public static OrderDetailPK getOrderDetailPK(final long id) {
        final OrderDetailPK orderDetailPK = new OrderDetailPK();
        orderDetailPK.setOrderId(id);
        orderDetailPK.setProductId(id);
        return orderDetailPK;
    }

    public static OrderDetailDTO getOrderDetailDTO() {
        return new OrderDetailDTO(getOrderDetailPK(1), getOrderDetail().getDiscount(), getOrderDetail().getQuantity(),
                                  getOrderDetail().getUnitPrice());
    }

}
