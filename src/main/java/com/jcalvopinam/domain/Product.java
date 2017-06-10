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
 * The persistent class for the env_products database table.
 */
package com.jcalvopinam.domain;

import com.jcalvopinam.dto.ProductDTO;
import org.apache.commons.lang.builder.ToStringBuilder;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("productId", productId)
                .append("description", description)
                .append("name", name)
                .append("quantityPerUnit", quantityPerUnit)
                .append("unitPrice", unitPrice)
                .append("orderDetails", orderDetails)
                .toString();
    }

}