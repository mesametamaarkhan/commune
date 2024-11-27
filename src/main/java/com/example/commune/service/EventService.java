package com.example.commune.service;

import com.example.commune.dto.EventDTO;
import com.example.commune.model.Event;
import com.example.commune.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<EventDTO> getAllEvents(Integer userId) {
        return eventRepository.findAll(userId);
    }
}
