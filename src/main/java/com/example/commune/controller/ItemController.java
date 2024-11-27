package com.example.commune.controller;

import com.example.commune.dto.ItemDTO;
import com.example.commune.model.Item;
import com.example.commune.service.ItemService;
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
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/marketplace")
    public String getItems(Model model, HttpSession session) {
        try {
            if ((Boolean) session.getAttribute("loggedIn")) {
                List<ItemDTO> items = itemService.getAllItems((Integer) session.getAttribute("userId"));
                Collections.reverse(items);
                model.addAttribute("items", items);
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "marketplace";
            }
            return "redirect:/";
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/marketplace")
    public String postItems(@ModelAttribute Item item, Model model, HttpSession session) {
        item.setSellerId((Integer) session.getAttribute("userId"));
        item.setDatePosted(new Date());
        item.setStatus("Available");
        itemService.saveItem(item);
        return "redirect:/marketplace";
    }

    @GetMapping("/marketplace/contact")
    public ResponseEntity<Map<String, String>> getSellerContactInfo(@RequestParam("id") Integer sellerId) {
        Map<String, String> contactInfo = itemService.getSellerContactInfo(sellerId);
        if(contactInfo != null) {
            return ResponseEntity.ok(contactInfo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Contact information not found"));
    }
}
