package com.demo.clearingfeed.controller;

import com.demo.clearingfeed.dto.LoginDTO;
import com.demo.clearingfeed.dto.userDTO;
import com.demo.clearingfeed.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.clearingfeed.repo.response.LoginResponse;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class LoginController {
	
	@Autowired
	private userService userservice;
	
	 @PostMapping(path = "save")
	    public String saveUser(@RequestBody userDTO userDTO)
	    {
	        String id = userservice.addUser(userDTO);
	        return id;
	    }
	 @PostMapping(path = "login")
	    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
	    {
	        LoginResponse loginResponse = userservice.loginUser(loginDTO);
	        return ResponseEntity.ok(loginResponse);
	    }

}
