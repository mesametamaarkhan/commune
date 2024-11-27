package com.example.commune.dto;

import com.example.commune.model.PollOptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PollAndSurveyDTO {
    private Integer pollId;
    private String title;
    private String description;
    private String createdBy;
    private Date createdDate;
    private List<PollOptions> options;
    private Integer selectedOptionId;
}
