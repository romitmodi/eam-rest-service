package com.lbg.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empname")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private EmployeeRole role;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "teamid", referencedColumnName = "id")
    private TeamDetails teamDetails;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Set<RequestDetails> requestDetails = new HashSet<>();

    public Employee(String name) {
        this.name=name;
    }

    public Employee() {
    }

    public Employee(String username, String password, String email) {
        this.name = username;
        this.password = password;
        this.email = email;

    }
}
