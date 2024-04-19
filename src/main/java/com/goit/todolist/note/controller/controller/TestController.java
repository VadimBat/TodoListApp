package com.goit.todolist.note.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TestController for test.
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public String test(@RequestParam(name = "name", required = false, defaultValue = "My Friend!")
                           String name, Model model){
        model.addAttribute("name", name);
        return "test";
    }

}
