package com.lbg.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "labdetails")
@Getter
@Setter
public class LabDetails {

    @Id
    @Column(name = "labid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "labname")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
    private BusinessUnit businessUnit;

}
