package com.lbg.hackathon.entity;

import com.lbg.hackathon.model.ERoleEmployee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticketdetails")
@Getter
@Setter
public class TicketDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ticket_status")
    private String status;

    @Column(name = "url")
    private String url;


}
