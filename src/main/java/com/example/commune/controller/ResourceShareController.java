package com.example.commune.controller;


import com.example.commune.dto.ResourceShareDTO;
import com.example.commune.model.ResourceShare;
import com.example.commune.service.ResourceShareService;
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
public class ResourceShareController {

    private final ResourceShareService resourceShareService;

    @GetMapping("/resourcesharing")
    public String getResourceShare(Model model, HttpSession session) {
        try {
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<ResourceShareDTO> sharedResources = resourceShareService.getAllResourceShares((Integer) session.getAttribute("userId"));
                Collections.reverse(sharedResources);
                model.addAttribute("sharedResources", sharedResources);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "resourcesharing";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/resourcesharing")
    public String postResourceShare(@ModelAttribute ResourceShare resourceShare, Model model, HttpSession session) {
        resourceShare.setSharedDate(new Date());
        resourceShare.setSharedBy((Integer) session.getAttribute("userId"));
        resourceShare.setStatus("Available");
        resourceShareService.save(resourceShare);
        return "redirect:/resourcesharing";
    }

    @GetMapping("/resourcesharing/contact")
    public ResponseEntity<Map<String, String>> getSharerContactInfo(@RequestParam("id") Integer sharerId) {
        Map<String, String> contactInfo = resourceShareService.getSharerContactInfo(sharerId);
        if(contactInfo != null) {
            return ResponseEntity.ok(contactInfo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Contact information not found"));
    }
}
