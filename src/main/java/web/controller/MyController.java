package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class MyController {

    private final UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(ModelMap model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers",allUsers);
        return "all-users";
    }

    @GetMapping("/updateUser")
    public String updateById(@RequestParam(value = "id",required = false) Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "info-user";
    }

    @GetMapping ("/addNewUser")
    public String addUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user",user);
        return "info-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id",required = false) Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}

