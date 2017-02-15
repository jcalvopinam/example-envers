/**
 * DTO to mapping of Order attributes
 */
package com.jcalvopinam.dto;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public class OrderDTO {

    private int id;
    private int orderStatus;
    private String saleDate;
    private int customer;
    private int employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

}
