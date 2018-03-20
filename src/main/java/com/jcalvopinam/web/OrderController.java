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

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAllOrders() {
        logger.info("Find all orders");
        return orderService.findAll();
    }

    @GetMapping(path = "/{text}")
    public List<Order> findByTest(@PathVariable String text) {
        logger.info(String.format("Finding by: %s", text));
        return orderService.findByText(text, text, text, text, text);
    }

    @PostMapping
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        logger.info(String.format("Saving order: %s", orderDTO.toString()));
        return orderService.save(orderDTO);
    }

    @PutMapping
    public String updateOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        logger.info(String.format("Updating order: %s", orderDTO.toString()));
        return orderService.update(orderDTO);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteOrder(@PathVariable int id) {
        logger.info(String.format("Deleting order: %s", id));
        return orderService.deleteById(id);
    }

}
