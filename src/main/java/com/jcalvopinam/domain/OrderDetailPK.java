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