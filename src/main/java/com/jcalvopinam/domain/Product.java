/**
 * The persistent class for the env_products database table.
 */
package com.jcalvopinam.domain;

import com.jcalvopinam.dto.ProductDTO;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Entity
@Audited
@Table(name = "env_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 7309958533611524176L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity_per_unit")
    private int quantityPerUnit;

    @Column(name = "unit_price")
    private double unitPrice;

    /**
     * bi-directional many-to-one association to OrderDetail
     * mappedBy must have the same name as object that was defined in OrderDetail entity
     * check the attribute 'private Product product'
     */
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    public Product() {
    }

    public Product(ProductDTO productDTO) {
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.quantityPerUnit = productDTO.getQuantityPerUnit();
        this.unitPrice = productDTO.getUnitPrice();
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityPerUnit() {
        return this.quantityPerUnit;
    }

    public void setQuantityPerUnit(int quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        getOrderDetails().add(orderDetail);
        orderDetail.setProduct(this);

        return orderDetail;
    }

    public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
        getOrderDetails().remove(orderDetail);
        orderDetail.setProduct(null);

        return orderDetail;
    }

}