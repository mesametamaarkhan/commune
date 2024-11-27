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
public class LostAndFoundDTO {
    private Integer lostFoundId;
    private String itemName;       // Corresponding to ItemName NVARCHAR(100)
    private String description;    // Corresponding to Description TEXT
    private String fullName ;        // Corresponding to UserID INT
    private Date reportDate;
}
