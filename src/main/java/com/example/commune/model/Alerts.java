package com.example.commune.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("Alerts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alerts {

    @Id
    @Column("AlertID")
    private String alertID;

    @Column("Description")
    private String description;

    @Column("IssuedBy")
    private Integer issuedBy;

    @Column("IssuedDate")
    private Date issuedDate;
}
