package com.example.commune.controller;

import com.example.commune.dto.EventDTO;
import com.example.commune.dto.PollAndSurveyDTO;
import com.example.commune.dto.PollRequestDTO;
import com.example.commune.model.PollAndSurvey;
import com.example.commune.model.PollOptions;
import com.example.commune.model.PollVotes;
import com.example.commune.service.PollAndSurveyService;
import com.example.commune.service.PollOptionsService;
import com.example.commune.service.PollVotesService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PollController {

    private final PollAndSurveyService pollAndSurveyService;
    private final PollOptionsService pollOptionsService;
    private final PollVotesService pollVotesService;

    @GetMapping("/polls")
    public String getAllPolls(Model model, HttpSession session) {
        try {
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<PollAndSurveyDTO> polls = pollAndSurveyService.getAllPolls((Integer) session.getAttribute("userId"));
                for (PollAndSurveyDTO poll : polls) {
                    List<PollOptions> options = pollOptionsService.getAllPollOptions(poll.getPollId());
                    poll.setOptions(options);

                    PollVotes pollVote = pollVotesService.getUserVoteForPoll((Integer) session.getAttribute("userId"), poll.getPollId());
                    if (pollVote != null) {
                        poll.setSelectedOptionId(pollVote.getOptionId());
                    }
                }
                model.addAttribute("polls", polls);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "polls";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }

    }

    @PostMapping("/polls")
    public String addPoll(@RequestBody PollRequestDTO pollRequestDTO, Model model, HttpSession session) {
        pollRequestDTO.setCreatedBy((Integer) session.getAttribute("userId"));
        PollAndSurvey poll = pollAndSurveyService.save(pollRequestDTO);
        pollOptionsService.save(pollRequestDTO, poll.getPollID());
        return "redirect:/polls";
    }

    @PostMapping("/polls/vote")
    public ResponseEntity<String> vote(@RequestBody PollVotes pollVote, HttpSession session) {
        pollVote.getId().setUserId((Integer) session.getAttribute("userId"));
        boolean success = pollVotesService.castVote(pollVote);
        if (success) {
            pollOptionsService.incrementOptionSelectCount(pollVote.getOptionId(), pollVote.getId().getPollId());
            return ResponseEntity.ok("Vote cast successfully.");
        } else {
            return ResponseEntity.status(409).body("User has already voted for this poll.");
        }
    }
}
