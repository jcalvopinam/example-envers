package com.jcalvopinam.dto;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private int quantityPerUnit;
    private double unitPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(int quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return String.format("ProductDTO{ id=%d, name='%s', description='%s', quantityPerUnit=%d, unitPrice=%.2f}", id,
                             name, description, quantityPerUnit, unitPrice);
    }

}
