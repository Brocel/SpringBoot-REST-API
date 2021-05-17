package com.brocels.springboot.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "city")
    private String city;

    public User() {

    }
}
