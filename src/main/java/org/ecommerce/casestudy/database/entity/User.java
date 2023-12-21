package org.ecommerce.casestudy.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name="user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "password")
    private String password;

    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserRole userRole;


    @PostPersist
    public void afterUserPersist() {
        // Create a new UserRole and associate it with the user
        UserRole role = new UserRole();
        role.setUser(this);
        role.setUserRole("USER");

        // Set the UserRole in the User entity
        this.setUserRole(role);
    }
}

