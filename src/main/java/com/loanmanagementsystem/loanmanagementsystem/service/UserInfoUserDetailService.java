package com.loanmanagementsystem.loanmanagementsystem.service;

import com.loanmanagementsystem.loanmanagementsystem.config.UserInfoUserDetails;
import com.loanmanagementsystem.loanmanagementsystem.config.UserRepository;
import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userInfo = userRepository.findByEmail(username);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo.get());
        if(userDetails == null){
            throw new UsernameNotFoundException("user not found");
        }
        return userDetails;
    }
}
