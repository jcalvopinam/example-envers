package com.jcalvopinam.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcalvopinam.domain.Order;
import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.OrderDTO;
import com.jcalvopinam.repository.OrderRepository;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.utils.Utilities;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    private String response;

    public OrderServiceImpl(OrderRepository orderRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByText(String id, String customer, String employee, String date, String orderStatus) {
        Integer orderId = Utilities.isInteger(id);
        Integer customerId = Utilities.isInteger(customer);
        Integer employeeId = Utilities.isInteger(employee);
        Date valueDate = Utilities.matchDate(date);

        return (customerId == 0 || employeeId == 0)
                ? orderRepository.findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(orderId, customer, customer, employee, employee, valueDate, orderId)
                : orderRepository.findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(orderId, customerId, employeeId, valueDate, orderId);
    }

    @Override
    public String save(OrderDTO orderDTO) {
        response = "Order saved!";
        Person customer = personRepository.findOne(orderDTO.getCustomer());
        Person employee = personRepository.findOne(orderDTO.getEmployee());
        orderRepository.save(new Order(orderDTO, customer, employee));
        logger.info(response);
        return response;
    }

    @Override
    public String update(OrderDTO orderDTO) {
        response = "Person updated!";
        Order order = orderRepository.findOne(orderDTO.getId());
        Person employee = personRepository.findOne(orderDTO.getEmployee());
        Person customer = personRepository.findOne(orderDTO.getCustomer());
        order = this.updateOrder(order, orderDTO, employee, customer);
        orderRepository.save(order);
        logger.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "Order deleted!";
        orderRepository.delete(id);
        logger.info(response);
        return response;
    }

    private Order updateOrder(Order order, OrderDTO orderDTO, Person employee, Person customer) {
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setSaleDate(Utilities.matchDate(orderDTO.getSaleDate()));
        return order;
    }

}
