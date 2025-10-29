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

package com.jcalvopinam.service.impl;

import com.jcalvopinam.controller.BaseControllerTest;
import com.jcalvopinam.domain.Order;
import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.domain.Product;
import com.jcalvopinam.dto.ProductDTO;
import com.jcalvopinam.repository.OrderDetailRepository;
import com.jcalvopinam.repository.OrderRepository;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.jcalvopinam.utils.DummyOrder.getOrderDTO;
import static com.jcalvopinam.utils.DummyOrder.getOrders;
import static com.jcalvopinam.utils.DummyOrderDetail.getOptionalOrderDetail;
import static com.jcalvopinam.utils.DummyOrderDetail.getOrderDetail;
import static com.jcalvopinam.utils.DummyOrderDetail.getOrderDetailDTO;
import static com.jcalvopinam.utils.DummyOrderDetail.getOrderDetailPK;
import static com.jcalvopinam.utils.DummyOrderDetail.getOrderDetails;
import static com.jcalvopinam.utils.DummyPerson.getOptionalPerson;
import static com.jcalvopinam.utils.DummyProduct.getProductDTO;

/**
 * @author Juan Calvopina
 */
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OrderDetailServiceImplTest extends BaseControllerTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    @InjectMocks
    private OrderServiceImpl orderService;
    @InjectMocks
    private OrderDetailServiceImpl orderDetailService;

    @Test
    void findAll() {
        Mockito.when(orderDetailRepository.findAll())
               .thenReturn(getOrderDetails());
        final List<OrderDetail> all = orderDetailService.findAll();
        Assertions.assertEquals(getOrderDetails().size(), all.size(), "It is missing products");
    }

    @Test
    void findByDetailPk() {
        Mockito.when(orderDetailRepository.findById(Mockito.any()))
               .thenReturn(getOptionalOrderDetail());
        final Optional<OrderDetail> all = orderDetailRepository.findById(getOrderDetailPK(1L));
        Assertions.assertTrue(all.isPresent(), "It is missing products");
    }

    @Test
    void save() {
        Mockito.when(personRepository.findById(1L))
               .thenReturn(getOptionalPerson());

        Mockito.when(orderRepository.save(Mockito.any()))
               .thenReturn(getOrders().get(0));

        final Order order = orderService.save(getOrderDTO());
        Assertions.assertNotNull(order.getOrderId(), "The id is null");

        final ProductDTO productDTO = getProductDTO();
        productDTO.setProductId(null);

        Mockito.when(productRepository.findById(Mockito.any()))
               .thenReturn(Optional.empty());

        Mockito.when(productRepository.save(Mockito.any()))
               .thenReturn(new Product(productDTO));

        final Product productSaved = productService.save(productDTO);
        Assertions.assertNotNull(productSaved.getName(), "The id is null");

        Mockito.when(orderDetailRepository.save(Mockito.any()))
               .thenReturn(getOrderDetail());

        final OrderDetail orderDetail = orderDetailService.save(getOrderDetailDTO());
        Assertions.assertNotNull(orderDetail.getId(), "The id is null");
    }

    @Test
    void update() {
        Mockito.when(orderDetailRepository.findById(Mockito.any()))
               .thenReturn(getOptionalOrderDetail());

        Mockito.when(orderDetailRepository.save(Mockito.any()))
               .thenReturn(getOrderDetail());

        final OrderDetail orderDetail = orderDetailService.update(getOrderDetailDTO());
        Assertions.assertNotNull(orderDetail.getId(), "The id is null");
    }

    @Test
    void deleteById() {
        Mockito.doNothing()
               .when(orderDetailRepository)
               .deleteById(getOrderDetailPK(1L));
        orderDetailService.deleteById(getOrderDetailPK(1L));
    }

}