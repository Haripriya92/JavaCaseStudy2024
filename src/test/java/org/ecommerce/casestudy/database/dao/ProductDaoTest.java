package org.ecommerce.casestudy.database.dao;

import jakarta.transaction.Transactional;
import org.ecommerce.casestudy.database.entity.Product;
import org.ecommerce.casestudy.formbean.ProductFormBean;
import org.ecommerce.casestudy.service.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductService productService;

    private Product addedProduct;
    String filename = "example-image.jpg";

    @BeforeEach
    public void setup() {
        // Arrange
        ProductFormBean form = new ProductFormBean();
        form.setProductName("ExampleProduct");
        form.setProductDescription("Example Description");
        form.setRating(4);
        form.setPrice(50);
        // Act
        addedProduct = productService.addNewProduct(form, filename);
    }

    @Test
    public void testFindByProductName() {
        // Additional test for findByProductName
        Product foundProduct = productDao.findByproductName("ExampleProduct");

        // Assert
        assertNotNull(foundProduct);
        assertEquals("ExampleProduct", foundProduct.getProductName());
    }


    @Test
    public void testFindByProductId() {
        // Additional test for findByProductId
        Product foundProduct = productDao.findByproductId(addedProduct.getId());

        // Assert
        assertNotNull(foundProduct);
        assertEquals("ExampleProduct", foundProduct.getProductName());
        // Add more assertions based on your actual product properties
    }

    @AfterEach
    @Transactional
    @Rollback
    public void cleanup() {
        // You can perform additional cleanup here if needed
        productDao.deleteByProductName(addedProduct.getProductName()); // Example: Deleting all products from the database
    }
}
