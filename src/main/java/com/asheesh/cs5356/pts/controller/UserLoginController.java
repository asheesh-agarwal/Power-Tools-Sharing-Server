package com.asheesh.cs5356.pts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asheesh.cs5356.pts.entity.User;
import com.asheesh.cs5356.pts.repository.UserRepository;
import com.asheesh.cs5356.pts.request.UserLoginRequest;
import com.asheesh.cs5356.pts.response.UserLoginResponse;

@RestController
public class UserLoginController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/loginUser")
	public @ResponseBody UserLoginResponse loginUserByEmail(@RequestBody UserLoginRequest request) {

		UserLoginResponse loginResponse = new UserLoginResponse();

		User user = userRepository.findByEmailId(request.getEmailId());

		if (user == null)
			return (UserLoginResponse) loginResponse.createErrorResponse("User not found, try with valid credentials");

		if (!verifyPasswords(user.getPassword(), request.getPassword().getBytes())) {
			return (UserLoginResponse) loginResponse
					.createErrorResponse("User authentication failed, try with valid credentials");
		}

		loginResponse.setUserId(user.getUserId());

		return loginResponse;
	}

	private boolean verifyPasswords(byte[] passwordOne, byte[] passwordTwo) {
		if (passwordOne == null || passwordTwo == null || passwordOne.length != passwordTwo.length)
			return false;

		for (int i = 0; i < passwordOne.length; i++) {
			if (passwordOne[i] == passwordTwo[i])
				continue;

			else
				return false;
		}

		return true;
	}
}
