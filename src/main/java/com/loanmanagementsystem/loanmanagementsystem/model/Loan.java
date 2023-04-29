package com.loanmanagementsystem.loanmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LoanId")
    private int loanId;
    @Column(name = "loanamount")
    private long lonaAmount;
    @Column(name = "Loantype")
    private String loanType;
    @Column(name = "Time")
    private double time;

    @Column(name = "Date")
    private String applyDate;

    @Column(name = "emi")
    private long emi;
    @Column(name = "isApproved")
    private String isApproved;

    @Column(name = "message")
    private String message;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel userModel;

    public Loan() {
    }

    public Loan(int loanId, long lonaAmount, String loanType, double time, String applyDate, long emi, String isApproved, String message, UserModel userModel) {
        this.loanId = loanId;
        this.lonaAmount = lonaAmount;
        this.loanType = loanType;
        this.time = time;
        this.applyDate = applyDate;
        this.emi = emi;
        this.isApproved = isApproved;
        this.message = message;
        this.userModel = userModel;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public long getLonaAmount() {
        return lonaAmount;
    }

    public void setLonaAmount(long lonaAmount) {
        this.lonaAmount = lonaAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public long getEmi() {
        return emi;
    }

    public void setEmi(long emi) {
        this.emi = emi;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
