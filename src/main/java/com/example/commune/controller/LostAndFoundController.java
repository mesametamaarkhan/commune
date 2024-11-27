package com.example.commune.controller;

import com.example.commune.dto.LostAndFoundDTO;
import com.example.commune.model.LostAndFoundItem;
import com.example.commune.service.LostAndFoundService;
import com.example.commune.service.UserService;
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
public class LostAndFoundController {

    private final LostAndFoundService lostAndFoundService;
    private final UserService userService;

    @GetMapping("/lostandfound")
    public String showLostAndFoundPage(Model model, HttpSession session) {
        try {
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<LostAndFoundDTO> lostAndFoundItems = lostAndFoundService.getLostAndFoundItems((Integer) session.getAttribute("userId"));
                Collections.reverse(lostAndFoundItems);
                model.addAttribute("items", lostAndFoundItems);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "lostandfound";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/lostandfound")
    public String createPost(@ModelAttribute LostAndFoundItem lostAndFoundItem, Model model, HttpSession httpSession) {
        lostAndFoundItem.setReportDate(new Date());
        lostAndFoundItem.setUserID((Integer) httpSession.getAttribute("userId"));
        lostAndFoundService.saveItem(lostAndFoundItem);
        return "redirect:/lostandfound";
    }


    @GetMapping("/lostandfound/contact")
    public ResponseEntity<Map<String, String>> getPosterContactInfo(@RequestParam("id") Integer posterId) {
        Map<String, String> contactInfo = lostAndFoundService.getPosterContactInfo(posterId);
        if(contactInfo != null) {
            return ResponseEntity.ok(contactInfo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Contact information not found"));
    }
}
