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
 * The primary key class for the order_details database table.
 */
package com.jcalvopinam.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Embeddable
public class OrderDetailPK implements Serializable {

    private static final long serialVersionUID = 1125212530915683308L;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    @Column(name = "order_id", insertable = false, updatable = false)
    private int orderId;

    public OrderDetailPK() {
    }

    public OrderDetailPK(int productId, int orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("productId", productId)
                .append("orderId", orderId)
                .toString();
    }

}