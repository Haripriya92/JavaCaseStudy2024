package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public User findByEmailIgnoreCase(String email);
    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User findByUserId(Integer id);

    @Query("SELECT u FROM User u")
    public List<User> getAllUsers();
}
