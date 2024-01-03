package org.ecommerce.casestudy.database.dao;


import org.ecommerce.casestudy.database.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartDao  extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.product.id = :productId")
    public Cart findByproductId(Integer productId);

    @Query("SELECT c FROM Cart c WHERE c.id = :cartId")
    public Cart findByCartId(Integer cartId);

    public List<Cart> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.id = :id")
    void deleteById(Integer id);

    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    public List<Cart> findByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.user.id = :userId")
    void deleteByUserId(Integer userId);
}
