package com.loanmanagementsystem.loanmanagementsystem.config;

import com.loanmanagementsystem.loanmanagementsystem.model.Loan;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Integer> {

    public List<Loan> findLoanByUserModel(UserModel userModel);

    public Loan findLoanByLoanId(int id);
    @Modifying
    @Transactional
    @Query("update Loan u set u.message =:message, u.isApproved =:status where u.loanId =:id")
    public void updateLoanByLoanId(@Param("message")String message,@Param("id") Integer id,@Param("status") String status);


    @Query("SELECT p from Loan p where concat(p.lonaAmount,' ', p.loanType,' ',p.time,' ',p.emi) LIKE %?1% ")
    public List<Loan> search(@Param("search") String search);

}
