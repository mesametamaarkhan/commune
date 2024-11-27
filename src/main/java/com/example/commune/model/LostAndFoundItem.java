package com.example.commune.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Date;

@Table("lostAndFoundItem")
@Getter
@Setter
public class LostAndFoundItem {

    @Id
    @Column("LostFoundID")
    private Integer lostFoundID;

    @Column("ItemName")
    private String itemName;

    @Column("Description")
    private String description;

    @Column("UserID")
    private Integer userID;

    @Column("ReportDate")
    private Date reportDate;
}
