package com.example.commune.controller;


import com.example.commune.dto.CarPoolDTO;
import com.example.commune.dto.EventDTO;
import com.example.commune.model.CarPool;
import com.example.commune.model.Event;
import com.example.commune.service.EventService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public String getEvents(Model model, HttpSession session) {
        try {
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<EventDTO> events = eventService.getAllEvents((Integer) session.getAttribute("userId"));
                Collections.reverse(events);
                model.addAttribute("events", events);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "events";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/events")
    public String postEvents(@ModelAttribute Event event, Model model, HttpSession session) {
        event.setOrganizerID((Integer) session.getAttribute("userId"));
        eventService.save(event);
        return "redirect:/events";
    }
}
