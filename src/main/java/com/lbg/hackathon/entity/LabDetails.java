package com.lbg.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labdetails")
@Getter
@Setter
public class LabDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "labname")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "labid")
    private Set<TeamDetails> teamDetails = new HashSet<>();

//    @ManyToOne(fetch = FetchType.EAGER,optional = false)
//    @JoinColumn(name = "bu_id", nullable = false)
//    private BusinessUnit businessUnit;

}
