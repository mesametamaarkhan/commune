package com.example.commune.service;

import com.example.commune.dto.EventDTO;
import com.example.commune.dto.PollRequestDTO;
import com.example.commune.key.PollOptionsKey;
import com.example.commune.model.PollOptions;
import com.example.commune.repository.PollOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollOptionsService {

    private final PollOptionsRepository pollOptionsRepository;

    public void save(PollRequestDTO pollRequest, Integer pollId) {
        List<String> options = pollRequest.getOptions();
        for(String optionText : options) {
            PollOptionsKey key = new PollOptionsKey(null, pollId);
            PollOptions pollOptions = new PollOptions();
            pollOptions.setOptionText(optionText);
            pollOptions.setId(key);
            pollOptionsRepository.save(pollOptions);
        }
    }

    public void incrementOptionSelectCount(Integer optionID, Integer pollID) {
        pollOptionsRepository.incrementOptionSelectCount(optionID, pollID);
    }

    public List<PollOptions> getAllPollOptions(Integer pollId) {
        return pollOptionsRepository.findByPollId(pollId);
    }

    public Optional<PollOptions> getOptionById(PollOptionsKey key) {
        return pollOptionsRepository.findById(key);
    }

}
