package com.example.commune.service;

import com.example.commune.dto.AlertDTO;
import com.example.commune.model.Alerts;
import com.example.commune.repository.AlertsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertsService {

    private final AlertsRepository alertsRepository;

    public Alerts save(Alerts alerts) {
        return alertsRepository.save(alerts);
    }

    public List<AlertDTO> getAllAlerts(Integer userId) {
        return alertsRepository.findAll(userId);
    }
}
