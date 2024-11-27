package com.example.commune.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("carpool")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarPool {

    @Id
    @Column("CarpoolID")
    private Integer carpoolID;

    @Column("DriverID")
    private Integer driverID;

    @Column("AvailableSeats")
    private Integer availableSeats;

    @Column("StartLocation")
    private String startLocation;

    @Column("Destination")
    private String destination;

    @Column("DepartureTime")
    private String departureTime;
}
