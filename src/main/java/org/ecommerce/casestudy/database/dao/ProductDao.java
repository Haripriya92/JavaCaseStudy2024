package org.ecommerce.casestudy.database.dao;
import org.ecommerce.casestudy.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p")
    public List<Product> getAllProducts();

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(:itemName)")
    List<Product> findBySearchNameIgnoreCase(String itemName);

    @Query("SELECT p FROM Product p JOIN ProductDetail pt ON p.id = pt.mainProduct.id WHERE LOWER(pt.productsType) = LOWER(:selectedValue)")
    List<Product> findBySelectedCategoryIgnoreCase(String selectedValue);

    @Query("SELECT p FROM Product p ORDER BY p.productPrice ASC")
    List<Product> findAllOrderByProductPriceAsc();

    @Query("SELECT p FROM Product p ORDER BY p.productPrice DESC")
    List<Product> findAllOrderByProductPriceDesc();


    @Query("SELECT p FROM Product p WHERE p.id = :selectedId")
    public Product findByproductId(Integer selectedId);


}
