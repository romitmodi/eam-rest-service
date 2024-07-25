package com.lbg.hackathon.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMessageDTO {

    private String messageType;
    private Long requestId;
    private ERoleEmployee role;
    private Long teamId;
    private Long empId;

}
