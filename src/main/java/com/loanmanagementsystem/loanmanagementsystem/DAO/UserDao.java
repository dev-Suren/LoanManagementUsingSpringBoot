package com.loanmanagementsystem.loanmanagementsystem.DAO;

import com.loanmanagementsystem.loanmanagementsystem.config.UserRepository;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllItems(){
        return (List<UserModel>) userRepository.findAll();
    }

    public void saveUsers(UserModel userModel){
        userRepository.save(userModel);
        System.out.println("done");
    }


    public UserModel getById(int id){
       return new UserModel();
    }

    public Optional<UserModel> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserModel getByemail(String email){
        return userRepository.getUserModelByEmail(email);
    }


    public List<UserModel> getDeactivateUser(){
        return userRepository.getAllUsers(false);
    }

    public void approveUser(int id){
        userRepository.udateUserById(true,id);
    }

}
