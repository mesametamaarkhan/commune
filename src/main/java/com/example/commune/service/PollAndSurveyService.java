package com.example.commune.service;

import com.example.commune.dto.EventDTO;
import com.example.commune.dto.PollAndSurveyDTO;
import com.example.commune.dto.PollRequestDTO;
import com.example.commune.model.PollAndSurvey;
import com.example.commune.repository.PollAndSurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PollAndSurveyService {

    private final PollAndSurveyRepository pollAndSurveyRepository;

    public PollAndSurvey save(PollRequestDTO pollRequest) {
        PollAndSurvey poll = new PollAndSurvey();
        poll.setTitle(pollRequest.getTitle());
        poll.setDescription(pollRequest.getDescription());
        poll.setCreatedBy(pollRequest.getCreatedBy());
        poll.setCreatedDate(new Date());
        return pollAndSurveyRepository.save(poll);
    }

    public List<PollAndSurveyDTO> getAllPolls(Integer userId) {
        return pollAndSurveyRepository.findAll(userId);
    }
}
