package com.demo.clearingfeed.service;


import com.demo.clearingfeed.dto.LoginDTO;
import com.demo.clearingfeed.dto.userDTO;
import com.demo.clearingfeed.repo.response.LoginResponse;

public interface userService {

	String addUser(userDTO userDTO);

	LoginResponse loginUser(LoginDTO loginDTO);

}
