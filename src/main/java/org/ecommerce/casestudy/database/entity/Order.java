package org.ecommerce.casestudy.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name="orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customer;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "shipped_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippedDate;

    @Column(name = "delivered_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "subtotal")
    private BigDecimal subTotal;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;
}
