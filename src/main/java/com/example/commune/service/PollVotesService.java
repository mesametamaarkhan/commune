package com.example.commune.service;

import com.example.commune.model.PollVotes;
import com.example.commune.repository.PollVotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollVotesService {

    private final PollVotesRepository pollVotesRepository;

    public PollVotes save(PollVotes pollVote) {
        return pollVotesRepository.save(pollVote);
    }

    public PollVotes getUserVoteForPoll(Integer userId, Integer pollId) {
        return pollVotesRepository.findByUserIdAndPollId(userId, pollId);
    }


    public boolean castVote(PollVotes pollVote) {
        boolean alreadyVoted = pollVotesRepository.hasUserVoted(pollVote.getId().getUserId(), pollVote.getId().getPollId());
        if (alreadyVoted) {
            return false;
        }
        pollVotesRepository.save(pollVote);
        return true;
    }
}
