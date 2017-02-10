/**
 * Methods for accessing the database
 */
package com.jcalvopinam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

import com.jcalvopinam.domain.Order;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * @param orderId
     * @param customer
     * @param employee
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(int orderId,
                                                                               int customer,
                                                                               int employee,
                                                                               Date date,
                                                                               int orderStatus);

    /**
     * @param orderId
     * @param customerName
     * @param customerLastName
     * @param employee
     * @param employeeLastName
     * @param date
     * @param orderStatus
     * @return
     */
    List<Order> findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(int orderId,
                                                                                                                                   String customerName,
                                                                                                                                   String customerLastName,
                                                                                                                                   String employee,
                                                                                                                                   String employeeLastName,
                                                                                                                                   Date date,
                                                                                                                                   int orderStatus);

}
