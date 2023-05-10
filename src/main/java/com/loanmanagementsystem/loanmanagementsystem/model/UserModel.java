package com.loanmanagementsystem.loanmanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Usertable")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private int userId;
    @Column(name = "Name")
    private String fName;
    @Column(name = "Last_name")
    private String lName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "Address")
    private String address;
    @Column(name = "isActive")
    private boolean isActive = false;
    @Column(name = "role")
    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "userModel",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Loan> loan;
    @Column(name = "ApprovedBy")
    private String approvedBy;



    public UserModel() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserModel(int userId, String email, String password, String address, boolean isActive, String role, List<Loan> loan, String approvedBy, String fName, String lName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.address = address;
        this.isActive = isActive;
        this.role = role;
        this.loan = loan;
        this.approvedBy = approvedBy;
        this.lName = lName;
        this.fName = fName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }


}
