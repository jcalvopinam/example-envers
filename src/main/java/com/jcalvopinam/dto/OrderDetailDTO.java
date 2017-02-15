package com.jcalvopinam.dto;

import com.jcalvopinam.domain.OrderDetailPK;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public class OrderDetailDTO {

    private OrderDetailPK id;
    private double discount;
    private int quantity;
    private double unitPrice;

    public OrderDetailPK getId() {
        return id;
    }

    public void setId(OrderDetailPK id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
