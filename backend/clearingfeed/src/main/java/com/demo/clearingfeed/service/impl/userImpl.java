package com.demo.clearingfeed.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.clearingfeed.dto.LoginDTO;
import com.demo.clearingfeed.dto.userDTO;
import com.demo.clearingfeed.entity.UserLogin;
import com.demo.clearingfeed.repo.userRepo;
import com.demo.clearingfeed.repo.response.LoginResponse;
import com.demo.clearingfeed.service.userService;

@Service
public class userImpl implements userService{

	
	@Autowired
	private userRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String addUser(userDTO userDTO) {
		
		  UserLogin user = new UserLogin(
	                userDTO.getId(),
	                userDTO.getName(),
	                userDTO.getEmail(),
	               this.passwordEncoder.encode(userDTO.getPassword())
	        );
	        userRepo.save(user);
	        return user.getName();
	}

	@Override
	public LoginResponse loginUser(LoginDTO loginDTO) {
	
	   
	        String msg = "";
	        UserLogin user1 = userRepo.findByEmail(loginDTO.getEmail());
	        if (user1 != null) {
	            String password = loginDTO.getPassword();
	            String encodedPassword = user1.getPassword();
	            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
	            if (isPwdRight) {
	                Optional<UserLogin> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
	                if (user.isPresent()) {
	                    return new LoginResponse("Login Success", true);
	                } else {
	                    return new LoginResponse("Login Failed", false);
	                }
	            } else {
	                return new LoginResponse("password Not Match", false);
	            }
	        }else {
	            return new LoginResponse("Email not exits", false);
	        }
	    
	
	}

	
}
