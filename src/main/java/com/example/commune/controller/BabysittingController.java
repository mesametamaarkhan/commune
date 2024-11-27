package com.example.commune.controller;

import com.example.commune.model.BabySitting;
import com.example.commune.model.User;
import com.example.commune.service.BabysittingService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BabysittingController {

    private final BabysittingService babysittingService;

    @GetMapping("/babysitting")
    public String getBabysitting(Model model, HttpSession session) {
        try{
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<BabySitting> babySittings = babysittingService.getAllBabysitters((Integer) session.getAttribute("userId"));
                Collections.reverse(babySittings);
                model.addAttribute("babysitting", babySittings);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "babysitting";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/babysitting")
    public String createBabysitting(@ModelAttribute BabySitting babySitting, Model model, HttpSession session) {
        babySitting.setUserID((Integer) session.getAttribute("userId"));
        babySitting.setPostDate(new Date());
        babysittingService.createBabysitter(babySitting);
        return "redirect:/babysitting";
    }

    @GetMapping("/babysitting/contact")
    public ResponseEntity<Map<String, String>> getBabySitterContact(@RequestParam("id") Integer babysitterId) {
        Map<String, String> contactInfo = babysittingService.getBabysitterContact(babysitterId);
        if(contactInfo != null) {
            return ResponseEntity.ok(contactInfo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Contact information not found"));
    }
}
