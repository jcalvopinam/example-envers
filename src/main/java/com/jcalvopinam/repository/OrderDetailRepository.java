package com.jcalvopinam.repository;

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.domain.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

    /**
     * @param orderDetailPK
     * @return
     */
    OrderDetail findById(OrderDetailPK orderDetailPK);

}
