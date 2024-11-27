package com.example.commune.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("Item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @Column("ItemID")
    private Integer itemId;

    @Column("ItemName")
    private String itemName;

    @Column("Description")
    private String description;

    @Column("Price")
    private String price;

    @Column("Status")
    private String status;

    @Column("Seller_ID")
    private Integer sellerId;

    @Column("Buyer_ID")
    private Integer buyerId;

    @Column("DatePosted")
    private Date datePosted;

}
