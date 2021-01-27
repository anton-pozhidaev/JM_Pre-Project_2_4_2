package study.jm.pp231.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.jm.pp231.model.User;
import study.jm.pp231.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        userService.addInitUsersToDB();
        this.userService = userService;
    }

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("usersAll", userService.listUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("usr", userService.get(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }
    /*
        @GetMapping("/new")
        public String newUser(Model model) {
            model.addAttribute("user", new User());
            return "users/new";
        }
    */

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }
    /*
        @PostMapping
        public String create(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email, @RequestParam("birthday") String birthday,
                             @RequestParam("address") String address) {

            User user = new User(firstName, lastName, email, birthday, address);
            userService.add(user);
            return "redirect:/users";
        }
    */

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.get(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public  String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
