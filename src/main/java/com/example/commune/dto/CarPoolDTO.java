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
public class CarPoolDTO {
    private Integer carpoolId;
    private Integer driverId;
    private String driverName;
    private Integer availableSeats;
    private String startLocation;
    private String destination;
    private Date departureTime;
    //private Date postDate;

}
