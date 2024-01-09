package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    @Order(1)
    public void createCustomerTest() {
        // given
        User user = new User();
        user.setFirstName("EMELY");
        user.setLastName("George");
        user.setEmail("emelygeorge123@gmail.com");
        user.setPhone(12345);
        user.setPassword("Password12");
        user.setCountry("US");

        // when
        user = userDao.save(user);

        // then
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("EMELY", user.getFirstName());
        Assertions.assertEquals("George", user.getLastName());
        Assertions.assertEquals("emelygeorge123@gmail.com", user.getEmail());
        Assertions.assertEquals(12345, user.getPhone());
        Assertions.assertEquals("Password12", user.getPassword());
        Assertions.assertEquals("US", user.getCountry());
    }

}
