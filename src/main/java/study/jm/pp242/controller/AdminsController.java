package study.jm.pp242.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.jm.pp242.model.User;
import study.jm.pp242.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private UserService userService;

    @Autowired
    public AdminsController(UserService userService) {
        userService.addInitUsersToDB();
        this.userService = userService;
    }

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("usersAll", userService.listUsers());
        return "users/users";
    }

//    @GetMapping("/id{id}")
//    public String show(@PathVariable("id") long id, Model model) {
//        model.addAttribute("usr", userService.get(id));
//        return "users/show";
//    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/id{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.get(id));
        return "users/edit";
    }

    @PatchMapping("/id{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/id{id}")
    public  String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
