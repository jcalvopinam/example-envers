package com.jcalvopinam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.domain.OrderDetailPK;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

    /**
     * 
     * @param orderDetailPK
     * @return
     */
    OrderDetail findById(OrderDetailPK orderDetailPK);

}
