package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public User findByEmailIgnoreCase(String email);
}
