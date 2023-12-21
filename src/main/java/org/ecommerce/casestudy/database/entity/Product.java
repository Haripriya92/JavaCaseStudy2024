package org.ecommerce.casestudy.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name="products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "added_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    @OneToMany(mappedBy = "mainProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> productTypes;


}

