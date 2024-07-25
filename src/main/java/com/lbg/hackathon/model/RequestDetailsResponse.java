package com.lbg.hackathon.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDetailsResponse {
    private Long id;
    private Long requestCreatedBy;
    private String status;
    private Long approverId;
    private String requestorRole;
    private Long requestorTeamId;
}
