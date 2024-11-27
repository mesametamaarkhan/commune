package com.example.commune.controller;

import com.example.commune.dto.CarPoolDTO;
import com.example.commune.model.CarPool;
import com.example.commune.service.CarPoolService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CarPoolController {

    private final CarPoolService carPoolService;

    @GetMapping("/carpool")
    public String getCarpools(Model model, HttpSession session) {
        try {
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<CarPoolDTO> carPools = carPoolService.getAllCarpools((Integer) session.getAttribute("userId"));
                Collections.reverse(carPools);
                model.addAttribute("carPools", carPools);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "carpool";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/carpool")
    public String postCarpool(@ModelAttribute CarPool carPool, Model model, HttpSession session) {
        // carPool.setPostDate(new Date());
        carPool.setDriverID((Integer) session.getAttribute("userId"));
        carPoolService.save(carPool);
        return "redirect:/carpool";
    }

    @GetMapping("/carpool/contact")
    public ResponseEntity<Map<String, String>> getDriverContactInfo(@RequestParam("id") Integer driverId) {
        Map<String, String> contactInfo = carPoolService.getDriverContactInfo(driverId);
        if(contactInfo != null) {
            return ResponseEntity.ok(contactInfo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Contact information not found"));
    }
}
