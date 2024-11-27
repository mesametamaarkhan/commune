package com.example.commune.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("resourceshare")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceShare {
    @Id
    @Column("ShareID")
    private Integer shareID;

    @Column("ResourceName")
    private String resourceName;

    @Column("SharedBy")
    private Integer sharedBy;

    @Column("BorrowerID")
    private Integer borrowerId;

    @Column("Status")
    private String status;

    @Column("SharedDate")
    private Date sharedDate;

}
