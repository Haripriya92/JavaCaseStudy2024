package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDetailDao extends JpaRepository<ProductDetail, Long> {
    @Query("SELECT pd FROM ProductDetail pd WHERE pd.mainProduct.id = :mainProductId AND pd.size = :size")
    ProductDetail findByMainProductIdAndSize(Integer mainProductId, String size);

    ProductDetail findById(Integer Id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO products_details (products_type, products_quantity, main_product_id, size) " +
            "VALUES (:productsType, :productsQuantity, :mainProductId, :size)", nativeQuery = true)
    void insertProductDetails(String productsType,
                               Integer productsQuantity,
                              Integer mainProductId,
                              String size);


}
