package com.example.commune.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Integer eventId;
    private String eventTitle;
    private String description;
    private String organizerName;
    private Date eventDate;
    private String location;


}
