package com.example.commune.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Getter
@Setter
public class User {

    @Id
    @Column("UserID")
    private Integer userID;

    @Column("FirstName") // maps to 'FirstName' column
    private String firstName;

    @Column("LastName") // maps to 'LastName' column
    private String lastName;

    @Column("Email") // maps to 'Email' column
    private String email;

    @Column("Role")
    private String role;

    @Column("Password") // maps to 'Password' column
    private String password;

    @Column("PhoneNumber") // maps to 'PhoneNumber' column
    private String phoneNumber;

    @Column("PostalCode") // maps to 'PostalCode' column
    private String postalCode;
}
