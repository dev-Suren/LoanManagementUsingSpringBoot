package com.loanmanagementsystem.loanmanagementsystem.DAO;

import com.loanmanagementsystem.loanmanagementsystem.config.LoanRepository;
import com.loanmanagementsystem.loanmanagementsystem.model.Loan;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanDao {
    @Autowired
    private LoanRepository loanRepository;

    public void saveLoan(Loan loan){
        loanRepository.save(loan);
    }


    public List<Loan> getAllLoan(){
        return (List<Loan>) loanRepository.findAll();
    }

    public List<Loan> findLoans(UserModel userModel){
        return loanRepository.findLoanByUserModel(userModel);
    }

    public Loan findLoanById(int id){
        return loanRepository.findLoanByLoanId(id);
    }

    public void updateLoan(Loan loan){
        loanRepository.save(loan);
    }

    public void updateLoanByid(String message, int id,String status){
        loanRepository.updateLoanByLoanId(message,id,status);
    }

    public List<Loan> search(String search){
       return loanRepository.search(search);
    }
}
