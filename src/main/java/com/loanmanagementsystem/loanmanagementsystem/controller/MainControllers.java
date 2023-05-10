package com.loanmanagementsystem.loanmanagementsystem.controller;

import com.loanmanagementsystem.loanmanagementsystem.config.UserInfoUserDetails;
import com.loanmanagementsystem.loanmanagementsystem.model.Loan;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import com.loanmanagementsystem.loanmanagementsystem.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainControllers {
    @Autowired
    private UserServices userServices;

    @GetMapping("/")
    public String dashboard(){
        return "normalusers";
    }

    @RequestMapping(value = "/register/reg", method = RequestMethod.POST)
    public String register(@ModelAttribute(value = "userModel") UserModel userModel){
        userServices.saveUser(userModel);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registarPage(){
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }





}
