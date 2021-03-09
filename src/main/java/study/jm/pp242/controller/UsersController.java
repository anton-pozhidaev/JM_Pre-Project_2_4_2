package study.jm.pp242.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.jm.pp242.model.User;
import study.jm.pp242.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showWithoutId(Model model, Principal principal) {
        model.addAttribute("usr", userService.findUserByUsername(principal.getName()));
        return "users/show";
    }
}
