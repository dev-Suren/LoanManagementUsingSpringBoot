package com.loanmanagementsystem.loanmanagementsystem.controller;

import com.loanmanagementsystem.loanmanagementsystem.Enum.LoanStatus;
import com.loanmanagementsystem.loanmanagementsystem.config.UserInfoUserDetails;
import com.loanmanagementsystem.loanmanagementsystem.model.Loan;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import com.loanmanagementsystem.loanmanagementsystem.service.LoanService;
import com.loanmanagementsystem.loanmanagementsystem.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;

@Controller
public class UserLoanControllers {
    @Autowired
    private UserServices userServices;

    @Autowired
    private LoanService loanService;

    @PreAuthorize("hasAuthority('NORMAL')")
    @RequestMapping(value = "/appliedloan",method= RequestMethod.GET)
    public ModelAndView appliedLoans(@AuthenticationPrincipal UserInfoUserDetails userDetails){
        UserModel userModel = userServices.getByEmail(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanobj",userModel.getLoan().stream().filter(e -> e.getIsApproved().equals(String.valueOf(LoanStatus.UNAPPROVED))).collect(Collectors.toList()));
        modelAndView.setViewName("normalappliedloans");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('NORMAL')")
    @GetMapping("/applyloan")
    public ModelAndView applyloan(){
        ModelAndView modelAndView = new ModelAndView();
         modelAndView.setViewName("applyloans");
         return modelAndView;
    }



    @PreAuthorize("hasAuthority('NORMAL')")
    @GetMapping("/approved")
    public ModelAndView approvedloans(@AuthenticationPrincipal UserInfoUserDetails userDetails){
        UserModel userModel = userServices.getByEmail(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        List<Loan> list = userModel.getLoan().stream().filter(e -> e.getIsApproved().equals(String.valueOf(LoanStatus.APPROVED))).collect(Collectors.toList());
        modelAndView.addObject("username",userModel.getfName());
        modelAndView.addObject("loanobj",list);
        modelAndView.setViewName("approvedloans");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('NORMAL')")
    @GetMapping("/unapprovedloan")
    public ModelAndView unapprovedloan(@AuthenticationPrincipal UserInfoUserDetails userDetails){
        UserModel userModel = userServices.getByEmail(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        List<Loan> list = userModel.getLoan().stream().filter(e -> e.getIsApproved().equals(String.valueOf(LoanStatus.REJECTED))).collect(Collectors.toList());
        System.out.println(list);
        modelAndView.addObject("loanobj",list);
        modelAndView.setViewName("unapprovedloan");
        return modelAndView;

    }

    @PostMapping("/post")
    public String post(@AuthenticationPrincipal UserInfoUserDetails userDetails,@ModelAttribute("loan") Loan loan){
        loan.setUserModel(userServices.getByEmail(userDetails.getUsername()));
        loan.setIsApproved(String.valueOf(LoanStatus.UNAPPROVED));
        loan.setApplyDate(dateFromats());
        loan.setEmi((long) emi_calculator(loan.getLonaAmount(), loan.getTime()));
        loanService.saveLoans(loan);
        return  "redirect:/dashboard";
    }



    private String dateFromats(){
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return dateObj.format(formatter);
    }


    private double emi_calculator(long p, double t)
    {
        double emi;
        double r = 7;

        r = r / (12 * 100);
        t = t * 12;
        emi = (p * r * pow(1 + r, t)) / (pow(1 + r, t) - 1);

        return (emi);
    }

}
