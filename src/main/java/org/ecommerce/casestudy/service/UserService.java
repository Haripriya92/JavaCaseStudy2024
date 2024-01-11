package org.ecommerce.casestudy.service;

import lombok.extern.slf4j.Slf4j;
import org.ecommerce.casestudy.database.dao.UserDao;
import org.ecommerce.casestudy.database.entity.Order;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.formbean.CreateUserFormBean;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User createNewUser(CreateUserFormBean form) {
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail().toLowerCase());
        user.setPassword(form.getPassword());
        String encoded = passwordEncoder.encode(form.getPassword());
        log.debug("Encoded password: " + encoded);
        user.setPassword(encoded);
        user.setPhone(form.getPhone());
        user.setCountry(form.getCountry());
        return userDao.save(user);
    }
}
