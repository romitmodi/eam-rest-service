package com.lbg.hackathon.entity;

import com.lbg.hackathon.model.ERoleEmployee;
import com.lbg.hackathon.model.EStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "requestdetails")
@Getter
@Setter
public class RequestDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;

    @Column(name = "approver_id")
    private Long approverId;

    @Column(name = "requestor_team_id")
    private Long requestorTeamId;

    @Column(name = "req_created_by")
    private Long requestCreatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "requestor_role")
    private ERoleEmployee requestorRole;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Set<TicketDetails> ticketDetails = new HashSet<>();

    public RequestDetails(Long requestorEmpId, EStatus status) {
        this.requestCreatedBy = requestorEmpId;
        this.status= status;
    }

    public RequestDetails() {
    }
}
