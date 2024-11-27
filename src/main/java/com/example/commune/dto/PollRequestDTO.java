package com.example.commune.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PollRequestDTO {
    private String title;
    private String description;
    private List<String> options;
    private Integer createdBy;
}