package com.loanmanagementsystem.loanmanagementsystem.controller;

import com.loanmanagementsystem.loanmanagementsystem.config.UserInfoUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NormalUsers {
    @PreAuthorize("hasAuthority('NORMAL')")
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserInfoUserDetails userDetails){
        return "normalusers";
    }
}
