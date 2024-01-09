package org.ecommerce.casestudy.database.dao;

import jakarta.transaction.Transactional;
import org.ecommerce.casestudy.database.entity.Order;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.service.OrderService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderService orderService;

    private Order addedOrder;

    @BeforeEach
    public void setup() {
        User user=userDao.findByUserId(15);
        addedOrder = orderService.updateOrderFromCart("1234", "TestAddress",user);
    }
    @Test
    public void testFindByOrderDetail() {
        Order foundOrder = orderDao.findByOrderDetail(addedOrder.getOrderNumber());

        assertNotNull(foundOrder);
        assertEquals(addedOrder.getOrderNumber(), foundOrder.getOrderNumber());
    }

    @AfterEach
    @Transactional
    @Rollback
    public void cleanup() {
        orderDao.deleteByOrderNumber(addedOrder.getOrderNumber());
    }
}
