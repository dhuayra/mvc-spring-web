package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.User;
import com.example.demo.repository.UsersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersRepository repo;

    @GetMapping({"", "/", "/users"})
    public String showUsers(Model model) {
        List<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "users/add";
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        // repo.save(user);        // Save the user to the database without validation
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        repo.save(newUser);
        return "redirect:/users";
    }
    
    @GetMapping("/edit/{id}")
    public String showEdit(Model model, @PathVariable int id) {
        User user = repo.findById(id).get();
        model.addAttribute("user", user);
        return "users/edit";
    }
    
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        User updateUser = repo.findById(user.getId()).get();
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        repo.save(updateUser);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = repo.findById(id).get();
        repo.delete(user);
        return "redirect:/users";
    }
    
    
    
}
