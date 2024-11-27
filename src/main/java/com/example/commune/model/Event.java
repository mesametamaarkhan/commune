package com.example.commune.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @Column("EventID")
    private Integer eventID;

    @Column("EventTitle")
    private String eventTitle;

    @Column("Description")
    private String description;

    @Column("OrganizerID")
    private Integer organizerID;

    @Column("EventDate")
    private String eventDate;

    @Column("Location")
    private String location;
}
