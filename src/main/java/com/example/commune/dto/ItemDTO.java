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
public class ItemDTO {
    private Integer itemId;
    private String itemName;
    private String sellerName;
    private Integer sellerId;
    private String Description;
    private String price;
    private Date postedDate;

}
