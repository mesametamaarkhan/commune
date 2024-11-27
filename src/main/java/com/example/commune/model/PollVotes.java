package com.example.commune.model;


import com.example.commune.key.PollVotesKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("pollvotes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PollVotes {
    private PollVotesKey id;
    private Integer optionId;
}
