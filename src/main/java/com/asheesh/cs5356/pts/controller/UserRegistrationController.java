package com.asheesh.cs5356.pts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asheesh.cs5356.pts.entity.User;
import com.asheesh.cs5356.pts.exception.UserNotFoundException;
import com.asheesh.cs5356.pts.repository.UserRepository;
import com.asheesh.cs5356.pts.request.UserRegistrationRequest;
import com.asheesh.cs5356.pts.request.UserTerminationRequest;
import com.asheesh.cs5356.pts.response.Response.Status;
import com.asheesh.cs5356.pts.response.UserRegistrationResponse;
import com.asheesh.cs5356.pts.response.UserTerminationResponse;

@RestController
public class UserRegistrationController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/registerUser")
	public @ResponseBody UserRegistrationResponse registerUserByEmail(@RequestBody UserRegistrationRequest request) {
		UserRegistrationResponse registrationResponse = new UserRegistrationResponse();

		if (StringUtils.isEmpty(request.getFirstName()))
			return (UserRegistrationResponse) registrationResponse.createErrorResponse("First Name cannot be empty");

		if (StringUtils.isEmpty(request.getLastName()))
			return (UserRegistrationResponse) registrationResponse.createErrorResponse("Last Name cannot be empty");

		if (StringUtils.isEmpty(request.getEmailId()))
			return (UserRegistrationResponse) registrationResponse.createErrorResponse("Email Id cannot be empty");

		if (StringUtils.isEmpty(request.getPassword()))
			return (UserRegistrationResponse) registrationResponse.createErrorResponse("Password cannot be empty");

		if (StringUtils.isEmpty(request.getConfirmPassword()))
			return (UserRegistrationResponse) registrationResponse
					.createErrorResponse("Confirm password cannot be empty");

		if (!request.getPassword().equals(request.getConfirmPassword()))
			return (UserRegistrationResponse) registrationResponse
					.createErrorResponse("Both password and confirm password should have same value");

		User user = userRepository.findByEmailId(request.getEmailId());

		if (user != null) {
			return (UserRegistrationResponse) registrationResponse
					.createErrorResponse("User already exists with Email Id: " + request.getEmailId());
		}

		user = userRepository.save(new User(request.getFirstName(), request.getLastName(), request.getEmailId(),
				request.getPassword().getBytes()));

		registrationResponse.setUserId(user.getUserId());
		registrationResponse.setStatus(Status.SUCCESS);

		return registrationResponse;
	}

	public void deleteUserByEmail(String emailId) throws UserNotFoundException {
		User user = userRepository.findByEmailId(emailId);

		if (user == null)
			throw new UserNotFoundException();

		userRepository.delete(user);
	}

	@RequestMapping(value = "/terminateUser")
	public @ResponseBody UserTerminationResponse terminateUser(@RequestBody UserTerminationRequest terminationRequest) {

		UserTerminationResponse terminationResponse = new UserTerminationResponse();

		User user = userRepository.findByEmailId(terminationRequest.getEmailId());

		if (user == null)
			return (UserTerminationResponse) terminationResponse
					.createErrorResponse("User not found with email Id: " + terminationRequest.getEmailId());

		if (!verifyPasswords(user.getPassword(), terminationRequest.getPassword().getBytes())) {
			// Increment invalid authentication counter and later lock the
			// account
			return (UserTerminationResponse) terminationResponse.createErrorResponse("User authentication failed");

		} else {
			userRepository.delete(user);
			terminationResponse.setStatus(Status.SUCCESS);

			return terminationResponse;
		}
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

	@RequestMapping(value = "/greetUser")
	public @ResponseBody UserRegistrationResponse greetUser() {
		return new UserRegistrationResponse("Hello Asheesh AGarwal, whoo!!");
	}
}
