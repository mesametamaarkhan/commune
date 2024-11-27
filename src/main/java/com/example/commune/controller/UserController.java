package com.example.commune.controller;

import com.example.commune.model.User;
import com.example.commune.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userEmail")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String index(HttpSession session, SessionStatus status) {
        session.getAttributeNames().asIterator().forEachRemaining(session::removeAttribute);
        session.invalidate();
        status.setComplete();
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession httpSession) {
        try {
            if((Boolean) httpSession.getAttribute("loggedIn")) {
                String Email = (String) httpSession.getAttribute("userEmail");
                if (Email == null) {
                    return "redirect:/index";
                }

                User user = userService.getUserByEmail(Email);
                if (user != null) {
                    model.addAttribute("user", user);
                } else {
                    return "redirect:/index";
                }
                return "dashboard";
            }
            else {
                return "redirect:/";
            }
        }
        catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession httpSession) {
        boolean isAuthenticated = userService.verifyUser(email, password);
        if (isAuthenticated) {
            User user = userService.getUserByEmail(email);
            httpSession.setAttribute("userEmail", email);
            httpSession.setAttribute("userId", user.getUserID());
            httpSession.setAttribute("loggedIn", true);
            httpSession.setAttribute("role", user.getRole());
            return "redirect:/dashboard";
        }
        else{
            model.addAttribute("error", "Invalid email or password");
            return "index";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setRole("User");
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/admin/add")
    public String goToAddAdmin(HttpSession session) {
        try {
            if(session.getAttribute("role").toString().equalsIgnoreCase("Admin")) {
                return "adminadd";
            }
            return "redirect:/dashboard";
        }
        catch(Exception e) {
            return "redirect:/dashboard";
        }
    }

    @PostMapping("/admin/add")
    public String addAdmin(@ModelAttribute User user, HttpSession session) {
        try {
            if(session.getAttribute("role").toString().equalsIgnoreCase("Admin")) {
                user.setRole("Admin");
                userService.createUser(user);
                return "redirect:/dashboard";
            }
            return "redirect:/dashboard";
        }
        catch(Exception e) {
            return "redirect:/dashboard";
        }
    }
}
