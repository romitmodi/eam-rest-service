package com.lbg.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "budetails")
@Getter
@Setter
public class BusinessUnit {

    @Id
    @Column(name = "bu_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bu_name")
    private String name;

}
