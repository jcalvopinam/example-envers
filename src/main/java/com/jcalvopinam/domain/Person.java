/**
 * The persistent class for the env_person database table.
 */
package com.jcalvopinam.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jcalvopinam.dto.PersonDTO;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Entity
@Audited
@Table(name = "env_person")
public class Person implements Serializable {

    private static final long serialVersionUID = -8284413697324924501L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    /**
     * bi-directional many-to-one association to Order
     * mappedBy must have the same name as object that was defined in Order entity
     * check the attribute 'private Person customer'
     */
    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private List<Order> customers;

    /**
     * bi-directional many-to-one association to Order
     * mappedBy must have the same name as object that was defined in Order entity
     * check the attribute 'private Person employee'
     */
    @JsonBackReference
    @OneToMany(mappedBy = "employee")
    private List<Order> employees;

    public Person() {
    }

    public Person(PersonDTO personDTO) {
        this.firstName = personDTO.getName();
        this.lastName = personDTO.getLastName();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Order> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<Order> customers) {
        this.customers = customers;
    }

    public Order addCustomer(Order order) {
        getCustomers().add(order);
        order.setCustomer(this);

        return order;
    }

    public Order removeCustomer(Order order) {
        getCustomers().remove(order);
        order.setCustomer(null);

        return order;
    }

    public List<Order> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Order> employees) {
        this.employees = employees;
    }

    public Order addEmployee(Order employee) {
        getEmployees().add(employee);
        employee.setEmployee(this);

        return employee;
    }

    public Order removeEmployee(Order employee) {
        getEmployees().remove(employee);
        employee.setEmployee(null);

        return employee;
    }

}