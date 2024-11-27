package com.example.commune.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceShareDTO {
    private Integer shareId;
    private String sharerId;
    private String resourceName;
    private String status;
    private String sharerName;
    private Date sharedDate;
}
