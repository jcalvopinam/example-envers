/*
 * MIT License
 *
 * Copyright (c) 2024 JUAN CALVOPINA M
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

package com.jcalvopinam.controller;

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.dto.OrderDetailDTO;
import com.jcalvopinam.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Juan Calvopina
 */
@RestController
@RequestMapping(value = "/order-details")
public class OrderDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetail.class);

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<OrderDetail>> findAllDetails() {
        LOGGER.info("Find all order details");
        return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}/{orderId}")
    public ResponseEntity<OrderDetail> findByDetailPk(@PathVariable final String productId,
                                                      @PathVariable final String orderId) {
        LOGGER.info("Finding by: {}, {}", productId, orderId);
        return new ResponseEntity<>(orderDetailService.findByDetailPk(productId, orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> saveDetail(@Validated @RequestBody final OrderDetailDTO orderDetailDTO) {
        LOGGER.info("Saving detail: {}", orderDetailDTO);
        return new ResponseEntity<>(orderDetailService.save(orderDetailDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OrderDetail> updateDetail(@Validated @RequestBody final OrderDetailDTO orderDetailDTO) {
        LOGGER.info("Updating order detail: {}", orderDetailDTO);
        return new ResponseEntity<>(orderDetailService.update(orderDetailDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDetail(@RequestBody final OrderDetailDTO orderDetailDTO) {
        LOGGER.info("Deleting order detail: {}", orderDetailDTO);
        orderDetailService.deleteById(orderDetailDTO.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
