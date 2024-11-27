package com.example.commune.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("pollandsurvey")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PollAndSurvey {

    @Id
    @Column("PollID")
    private Integer pollID;

    @Column("Title")
    private String title;

    @Column("Description")
    private String description;

    @Column("CreatedBy")
    private Integer createdBy;

    @Column("CreatedDate")
    private Date createdDate;

}
