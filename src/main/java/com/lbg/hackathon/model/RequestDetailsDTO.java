package com.lbg.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetailsDTO {

    private ERoleEmployee reqestorRole;
    private Long requestorTeamId;
    private Long requestorEmpId;
}
