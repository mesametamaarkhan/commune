package com.example.commune.controller;

import com.example.commune.model.Message;
import com.example.commune.model.User;
import com.example.commune.service.MessageService;
import com.example.commune.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/messages")
    public String getChatPage(Model model, HttpSession session) {
        try {
            if((Boolean) session.getAttribute("loggedIn")) {
                User currentUser = userService.getUserById((Integer) session.getAttribute("userId"));
                Iterable<User> users = userService.getAllUsers(currentUser);
                model.addAttribute("users", users);
                model.addAttribute("currentUserId", (Integer) session.getAttribute("userId"));
                model.addAttribute("role", (String) session.getAttribute("role"));
                return "messages";
            }
            else {

                return "redirect:/";
            }
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/messages/send")
    public ResponseEntity<Void> sendMessage(@RequestBody Message message) {
        message.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        message.setIsRead(false);
        messageService.sendMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/messages/unread")
    public ResponseEntity<List<Message>> getUnreadMessages(@RequestParam Integer receiverId) {
        List<Message> messages = messageService.getUnreadMessages(receiverId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/conversation")
    public ResponseEntity<List<Message>> getConversationMessages(@RequestParam Integer userId1, @RequestParam Integer userId2, Model model) {
        List<Message> messages = messageService.getConversation(userId1, userId2);
        User user = userService.getUserById(userId2);
        model.addAttribute("user", user);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/messages/markAsRead")
    public ResponseEntity<Void> markAsRead(@RequestParam Integer userId1, @RequestParam Integer userId2) {
        messageService.markMessagesAsRead(userId1, userId2);
        return ResponseEntity.ok().build();
    }
}
