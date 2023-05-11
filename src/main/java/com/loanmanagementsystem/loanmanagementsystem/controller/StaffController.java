package com.loanmanagementsystem.loanmanagementsystem.controller;

import com.loanmanagementsystem.loanmanagementsystem.Enum.LoanStatus;
import com.loanmanagementsystem.loanmanagementsystem.model.Loan;
import com.loanmanagementsystem.loanmanagementsystem.service.LoanService;
import com.loanmanagementsystem.loanmanagementsystem.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StaffController {
    @Autowired
    private LoanService loan;
    @Autowired
    private UserServices userServices;

    @GetMapping("/staff")
    @PreAuthorize("hasAuthority('STAFF')")
    public ModelAndView staffPage(){
        List<Loan> list =loan.getAllLoan().stream().filter(e -> e.getIsApproved().equals("UNAPPROVED")).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanobj",list);
        modelAndView.setViewName("staffhomepage");
        return modelAndView;
    }


    @GetMapping("/staff/approve/{id}")
    @PreAuthorize("hasAuthority('STAFF')")
    public String approveAction(@PathVariable int id){
        Loan loan1 = loan.findLoanByid(id);
        loan1.setIsApproved(String.valueOf(LoanStatus.APPROVED));
        System.out.println(loan1);
        loan.saveLoans(loan1);

        return "redirect:/staff";
    }

    @GetMapping("/staff/unapprove/{id}")
    @PreAuthorize("hasAuthority('STAFF')")
    public ModelAndView unapproveAction(@PathVariable int id){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loan",loan.findLoanByid(id));
        modelAndView.setViewName("staffunapproveloans");
        return modelAndView;
    }

    @PostMapping("/message/{id}")
    @PreAuthorize("hasAuthority('STAFF')")
    public String getMessage(@PathVariable int id, @ModelAttribute("message") String message){
        loan.updateLoanById(message,id);
        return "redirect:/staff";
    }

    @GetMapping("/search/{variable}")
    @PreAuthorize("hasAuthority('STAFF')")
    public ModelAndView search(@PathVariable("variable") String variable){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanobj",loan.search(variable));
        modelAndView.setViewName("search");
        System.out.println(variable);
        return  modelAndView;
    }


    @GetMapping("/userdetails")
    public ModelAndView authorized(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanobj",userServices.getDeactivateUser());
        modelAndView.setViewName("unauthorizeduser");
        return modelAndView;
    }


    @GetMapping("/staff/user/{id}")
    public ModelAndView appriveuser(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        userServices.approveUser(id);
        modelAndView.addObject("loanobj",userServices.getDeactivateUser());
        modelAndView.setViewName("redirect:/userdetails");
        return modelAndView;
    }

}
