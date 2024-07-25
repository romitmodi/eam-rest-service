package com.lbg.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empname")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private EmployeeRole role;

    @ManyToOne
    @JoinColumn(name = "teamid", referencedColumnName = "id")
    private TeamDetails teamDetails;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Set<RequestDetails> requestDetails = new HashSet<>();

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String username, String password, String email) {
        this.name = username;
        this.password = password;
        this.email = email;

    }
}
