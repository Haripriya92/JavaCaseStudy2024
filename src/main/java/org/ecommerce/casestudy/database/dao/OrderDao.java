package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.orderNumber = :orderNumber")
    public Order findByOrderDetail(String orderNumber);
}
