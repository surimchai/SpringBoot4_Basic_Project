package com.rookies6.myspringboot4project.controller;

import com.rookies6.myspringboot4project.entity.User;
import com.rookies6.myspringboot4project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");
        return "leaf";
    }
    /*
        public ModelAndView(String viewName, String modelName, Object modelObject)
     */
    @GetMapping("/index")
    public ModelAndView userList() {
        List<User> userList = userRepository.findAll();
        return new ModelAndView("index","users",userList);
    }

    @GetMapping("/signup")
    public String showSignUpForm(){
        return "add-user";
    }

}