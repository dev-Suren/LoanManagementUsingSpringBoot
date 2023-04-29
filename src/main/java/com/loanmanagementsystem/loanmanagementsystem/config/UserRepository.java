package com.loanmanagementsystem.loanmanagementsystem.config;

import com.loanmanagementsystem.loanmanagementsystem.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel,Integer> {

    public Optional<UserModel> findByEmail(String email);

    public UserModel getUserModelByEmail(String email);

    @Query("SELECT x from UserModel x where x.isActive =:value")
    public List<UserModel> getAllUsers(@Param("value") boolean bool);

    @Modifying
    @Transactional
    @Query("update UserModel u set u.isActive =:message where u.userId =:id")
    public void udateUserById(@Param("message")boolean message,@Param("id") Integer id);
}
