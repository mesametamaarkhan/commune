package com.example.commune.controller;

import com.example.commune.dto.AlertDTO;
import com.example.commune.model.Alerts;
import com.example.commune.service.AlertsService;
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
public class AlertsController {

    private final AlertsService alertsService;

    @GetMapping("/alerts")
    public String getAlerts(Model model, HttpSession session) {
        try {
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<AlertDTO> alerts = alertsService.getAllAlerts((Integer) session.getAttribute("userId"));
                Collections.reverse(alerts);
                model.addAttribute("alerts", alerts);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "alerts";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/alerts")
    public String postAlerts(@ModelAttribute Alerts alerts, Model model, HttpSession session) {
        alerts.setIssuedDate(new Date());
        alerts.setIssuedBy((Integer) session.getAttribute("userId"));
        alertsService.save(alerts);
        return "redirect:/alerts";
    }
}
