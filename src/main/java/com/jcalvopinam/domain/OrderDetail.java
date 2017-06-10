/**
 * The persistent class for the env_order_details database table.
 */
package com.jcalvopinam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jcalvopinam.dto.OrderDetailDTO;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Entity
@Audited
@Table(name = "env_order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1183975993716362588L;

    @EmbeddedId
    private OrderDetailPK id;

    @Column(name = "discount")
    private double discount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    /**
     * bi-directional many-to-one association to Order entity
     * JoinColumn name must have the same name as the Order entity Id, as well as the same
     * name as defined in the column name of OrderDetailPK entity, because this is part of composite pk
     * check the name of the column annotation @Column(name = "order_id") in Order entity
     */
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    /**
     * bi-directional many-to-one association to Product
     * JoinColumn name must have the same name as the Product entity Id, as well as the same
     * name as defined in the column name of OrderDetailPK entity, because this is part of composite pk
     * check the name of the column annotation @Column(name = "product_id") in Product entity
     */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @JsonIgnore
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailDTO orderDetailDTO) {
        this.id = orderDetailDTO.getId();
        this.quantity = orderDetailDTO.getQuantity();
        this.discount = orderDetailDTO.getDiscount();
        this.unitPrice = orderDetailDTO.getUnitPrice();
    }

    public OrderDetailPK getId() {
        return this.id;
    }

    public void setId(OrderDetailPK id) {
        this.id = id;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("discount", discount)
                .append("quantity", quantity)
                .append("unitPrice", unitPrice)
                .append("order", order)
                .append("product", product)
                .toString();
    }

}