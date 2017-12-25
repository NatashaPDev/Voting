package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.service.DishService;
import ru.javawebinar.topjava.util.DishesUtil;

@Controller
public class RootController {

    @Autowired
    private DishService dishService;

    @GetMapping("/")
    public String root() {
        return "redirect:dishes";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dishes")
    public String dishes(Model model) {
        model.addAttribute("dishes",
                DishesUtil.getWithExceeded(dishService.getAll(AuthorizedUser.id())));
        return "dishes";
    }
}
