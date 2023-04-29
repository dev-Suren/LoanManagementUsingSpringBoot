package com.loanmanagementsystem.loanmanagementsystem.controller;

import com.loanmanagementsystem.loanmanagementsystem.model.Loan;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import com.loanmanagementsystem.loanmanagementsystem.service.LoanService;
import com.loanmanagementsystem.loanmanagementsystem.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RestControllersImp {
    @Autowired
    private UserServices userServices;
    @Autowired
    private LoanService loanService;
    @GetMapping("/alluser")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        try{
            return ResponseEntity.of(Optional.of(userServices.getAllUsrs()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/saveusers")
    public ResponseEntity<UserModel> saveUsers(@RequestBody UserModel userModel){
        try{
            userServices.saveUser(userModel);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @PostMapping("/loanservice/{id}")
    public void saveLoan(@RequestBody Loan loan,@PathVariable("id") int id){
        try{
            loan.setUserModel(userServices.getObj(id));
            System.out.println(loan);
            loanService.loanServicesSave(loan);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public void updateLoan(@RequestParam("id") int id){
//        UserModel user = userServices.getObj(id);
//        System.out.println(user);
//
//    }

    @GetMapping("/getloans")
    public List<Loan> getAllLoan(){
        return loanService.getAllLoan();
    }


}
