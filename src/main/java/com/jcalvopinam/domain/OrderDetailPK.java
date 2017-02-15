/**
 * The primary key class for the order_details database table.
 */
package com.jcalvopinam.domain;

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

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrderDetailPK)) {
            return false;
        }
        OrderDetailPK castOther = (OrderDetailPK) other;
        return
                (this.productId == castOther.productId)
                        && (this.orderId == castOther.orderId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.productId;
        hash = hash * prime + this.orderId;

        return hash;
    }

    @Override
    public String toString() {
        return String.format("OrderDetailPK {productId=%s, orderId=%s}", productId, orderId);
    }

}