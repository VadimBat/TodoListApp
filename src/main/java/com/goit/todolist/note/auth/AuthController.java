package com.goit.todolist.note.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/auth")
@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/profile")
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("auth-page");

        result.addObject("username", authService.getUsername());

        return result;
    }
}
