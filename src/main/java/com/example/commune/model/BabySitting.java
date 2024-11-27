package com.example.commune.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("babysitting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BabySitting {

    @Id
    @Column("BabySitterID")
    private Integer id;

    @Column("UserID")
    private Integer userID;

    @Column("Name")
    private String name;

    @Column("Location")
    private String location;

    @Column("RatePerHour")
    private Integer ratePerHour;

    @Column("HoursAvailable")
    private String hoursAvailable;

    @Column("PostDate")
    private Date postDate;
}
