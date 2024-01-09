package org.ecommerce.casestudy.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @Column(name = "state")
    private String state;

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

