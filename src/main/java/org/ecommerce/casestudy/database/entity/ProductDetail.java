package org.ecommerce.casestudy.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Table(name="products_details")
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = " products_type")
    private String productsType;

    @Column(name = "products_quantity")
    private Integer productQuantity;

    @Column(name = "size")
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_product_id")
    private Product mainProduct;
/*
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;*/

}

