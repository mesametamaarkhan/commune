package com.example.commune.model;

import com.example.commune.key.PollOptionsKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("polloptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PollOptions {

    private PollOptionsKey id;

    @Column("OptionText")
    private String optionText;

    @Column("OptionSelectCount")
    private Integer optionSelectCount;
}
