package com.loanmanagementsystem.loanmanagementsystem.service;

import com.loanmanagementsystem.loanmanagementsystem.DAO.LoanDao;
import com.loanmanagementsystem.loanmanagementsystem.Enum.LoanStatus;
import com.loanmanagementsystem.loanmanagementsystem.model.Loan;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanService {
    @Autowired
    private LoanDao loanDao;

    public void loanServicesSave(Loan loan){
        loanDao.saveLoan(loan);
    }
    public List<Loan> getAllLoan(){
        return loanDao.getAllLoan();
    }

    public void saveLoans(Loan loan){
        loanDao.saveLoan(loan);
    }


    public List<Loan> findLoans(UserModel userModel){
        return loanDao.findLoans(userModel);
    }


    public Loan findLoanByid(int id){
        return loanDao.findLoanById(id);
    }

    public void updateLoan(Loan loan){
        loanDao.updateLoan(loan);
    }

    public void updateLoanById(String message, int id){

        loanDao.updateLoanByid(message,id, String.valueOf(LoanStatus.REJECTED));
    }

    public List<Loan> search(String search){
        return loanDao.search(search);
    }
}
