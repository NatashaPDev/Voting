package ru.natashapetrenko.voting.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

}
