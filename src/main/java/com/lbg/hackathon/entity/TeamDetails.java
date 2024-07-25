package com.lbg.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teamdetails")
@Getter
@Setter
public class TeamDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "teamname")
    private String name;

    @Column(name="EL_ID")
    private Long elId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "labid", referencedColumnName = "labid")
    private LabDetails labDetails;
}
