package org.ecommerce.casestudy.database.dao;

import org.ecommerce.casestudy.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    public List<UserRole> findByUserId(Integer userId);
}
