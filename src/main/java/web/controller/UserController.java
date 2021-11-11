package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("user")
    public String index(Principal principal, Model model) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("userInfo", user);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin/admin_panel";
    }

    @GetMapping("admin/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userById", userService.readUser(id));
        return "admin/admin_show";
    }

    @GetMapping("admin/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "admin/admin_new";
    }

    @PostMapping
    public String create(@ModelAttribute("redirectAll") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/update")
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("updateUser", userService.readUser(id));
        return "admin/admin_update";
    }

    @PatchMapping("admin/{id}")
    public String updated(@ModelAttribute("redirectAll") User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}