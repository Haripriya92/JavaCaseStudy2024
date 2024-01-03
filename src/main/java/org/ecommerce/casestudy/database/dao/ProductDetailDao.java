package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailDao extends JpaRepository<ProductDetail, Long> {
    @Query("SELECT pd FROM ProductDetail pd WHERE pd.mainProduct.id = :mainProductId AND pd.size = :size")
    ProductDetail findByMainProductIdAndSize(Integer mainProductId, String size);

    ProductDetail findById(Integer Id);


}
