package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDao  extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId AND od.productDetail.id = :productId")
   public OrderDetail findByOrderIdAndProductId(Integer orderId, Integer productId);
}
