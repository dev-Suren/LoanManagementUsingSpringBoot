package com.loanmanagementsystem.loanmanagementsystem.service;

import com.loanmanagementsystem.loanmanagementsystem.DAO.UserDao;
import com.loanmanagementsystem.loanmanagementsystem.Enum.UserType;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<UserModel> getAllUsrs(){
        return userDao.getAllItems();
    }


    public void saveUser(UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRole(String.valueOf(UserType.NORMAL));
        userDao.saveUsers(userModel);
    }

    public UserModel getObj(int id){
       return userDao.getById(id);
    }

    public Optional<UserModel> findByEmail(String email){
        return userDao.findByEmail(email);
    }
    public UserModel getByEmail(String email){
       return userDao.getByemail(email);
    }

    public List<UserModel> getDeactivateUser(){
        return userDao.getDeactivateUser();
    }

    public void approveUser(int id){
        userDao.approveUser(id);
    }


}
