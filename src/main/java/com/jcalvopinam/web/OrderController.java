package com.jcalvopinam.web;

import com.jcalvopinam.domain.Order;
import com.jcalvopinam.dto.OrderDTO;
import com.jcalvopinam.service.OrderService;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String WELCOME_ORDER_ENTITY = "Welcome to Envers example: Order Entity";

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String init() {
        return WELCOME_ORDER_ENTITY;
    }

    @RequestMapping(value = "/find-all-orders")
    public List<Order> findAllOrders() {
        logger.info("Find all orders");
        return orderService.findAll();
    }

    @RequestMapping(value = "/find-by-order")
    public List<Order> findByTest(@RequestParam (value = "text") String text){
        logger.info(String.format("Finding by: %s", text));
        return orderService.findByText(text, text, text, text, text);
    }

    @RequestMapping(value = "/save-order", method = RequestMethod.POST)
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        logger.info(String.format("Saving order: %s", orderDTO.toString()));
        return orderService.save(orderDTO);
    }

    @RequestMapping(value = "/update-order", method = RequestMethod.POST)
    public String updateOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        logger.info(String.format("Updating order: %s", orderDTO.toString()));
        return orderService.update(orderDTO);
    }

    @RequestMapping(value = "/delete-order", method = RequestMethod.GET)
    public String deleteOrder(@RequestParam(value = "id") int id) {
        logger.info(String.format("Deleting order: %s", id));
        return orderService.deleteById(id);
    }

}
