package com.jcalvopinam.web;

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.dto.OrderDetailDTO;
import com.jcalvopinam.service.OrderDetailService;
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
@RequestMapping(value = "/order-detail")
public class OrderDetailController {
    private static final Logger logger = LoggerFactory.getLogger(OrderDetail.class);
    private static final String WELCOME_ORDER_DETAIL_ENTITY = "Welcome to Envers example: Order Detail Entity";

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/")
    public String init() {
        return WELCOME_ORDER_DETAIL_ENTITY;
    }

    @RequestMapping(value = "/find-all-details", method = RequestMethod.GET)
    public List<OrderDetail> findAllDetails() {
        logger.info("Find all order details");
        return orderDetailService.findAll();
    }

    @RequestMapping(value = "/find-by-detail", method = RequestMethod.GET)
    public OrderDetail findByDetailPk(@RequestParam(value = "productId") String productId,
                                      @RequestParam(value = "orderId") String orderId) {
        logger.info(String.format("Finding by: %s, %s", productId, orderId));
        return orderDetailService.findByDetailPk(productId, orderId);
    }

    @RequestMapping(value = "/save-detail", method = RequestMethod.POST)
    public String saveDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        Validate.notNull(orderDetailDTO, "The order detail cannot be null");
        logger.info(String.format("Saving detail: %s", orderDetailDTO.toString()));
        return orderDetailService.save(orderDetailDTO);
    }

    @RequestMapping(value = "/update-detail", method = RequestMethod.POST)
    public String updateDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        Validate.notNull(orderDetailDTO, "The order detail cannot be null");
        logger.info(String.format("Updating order detail: %s", orderDetailDTO.toString()));
        return orderDetailService.update(orderDetailDTO);
    }

    @RequestMapping(value = "/delete-detail", method = RequestMethod.POST)
    public String deleteDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        logger.info(String.format("Deleting order detail: %s", orderDetailDTO));
        return orderDetailService.deleteById(orderDetailDTO.getId());
    }

}
