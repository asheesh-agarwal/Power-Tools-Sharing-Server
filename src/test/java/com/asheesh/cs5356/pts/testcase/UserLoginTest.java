package com.asheesh.cs5356.pts.testcase;

import org.springframework.web.client.RestTemplate;

import com.asheesh.cs5356.pts.request.UserLoginRequest;
import com.asheesh.cs5356.pts.request.UserRegistrationRequest;
import com.asheesh.cs5356.pts.request.UserTerminationRequest;
import com.asheesh.cs5356.pts.response.Response.Status;
import com.asheesh.cs5356.pts.response.UserLoginResponse;
import com.asheesh.cs5356.pts.response.UserRegistrationResponse;
import com.asheesh.cs5356.pts.response.UserTerminationResponse;

import junit.framework.TestCase;

public class UserLoginTest extends TestCase {

	private static final String REGISTER_USER_URI = "http://localhost:8080/registerUser";
	private static final String TERMINATE_USER_URI = "http://localhost:8080/terminateUser";
	private static final String LOGIN_USER_URI = "http://localhost:8080/loginUser";

	public UserLoginTest() {
	}

	public UserLoginTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		registerUser();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		terminateUser();
	}

	private void registerUser() {
		RestTemplate restTemplate = new RestTemplate();

		UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
		registrationRequest.setFirstName("First Name");
		registrationRequest.setLastName("Last Name");
		registrationRequest.setEmailId("first.last@gmail.com");
		registrationRequest.setPassword("password");
		registrationRequest.setConfirmPassword("password");

		restTemplate.postForObject(REGISTER_USER_URI, registrationRequest, UserRegistrationResponse.class);
	}

	private void terminateUser() {
		UserTerminationRequest terminationRequest = new UserTerminationRequest();

		terminationRequest.setEmailId("first.last@gmail.com");
		terminationRequest.setPassword("password");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(TERMINATE_USER_URI, terminationRequest, UserTerminationResponse.class);
	}

	public void testUserLoginWithValidCredentials() {
		RestTemplate restTemplate = new RestTemplate();

		UserLoginRequest loginRequest = new UserLoginRequest();
		loginRequest.setEmailId("first.last@gmail.com");
		loginRequest.setPassword("password");

		UserLoginResponse loginResponse = restTemplate.postForObject(LOGIN_USER_URI, loginRequest,
				UserLoginResponse.class);

		assertEquals(Status.SUCCESS, loginResponse.getStatus());
		assertNotNull("User login failed, userId is null", loginResponse.getUserId());
	}

	public void testUserLoginWithInvalidPassword() {
		RestTemplate restTemplate = new RestTemplate();

		UserLoginRequest loginRequest = new UserLoginRequest();
		loginRequest.setEmailId("first.last@gmail.com");
		loginRequest.setPassword("invalid");

		UserLoginResponse loginResponse = restTemplate.postForObject(LOGIN_USER_URI, loginRequest,
				UserLoginResponse.class);

		assertEquals(Status.ERROR, loginResponse.getStatus());
		assertEquals("User authentication failed, try with valid credentials", loginResponse.getErrorMessage());
	}

	public void testUserLoginWithInvalidEmailId() {
		RestTemplate restTemplate = new RestTemplate();

		UserLoginRequest loginRequest = new UserLoginRequest();
		loginRequest.setEmailId("name@gmail.com");
		loginRequest.setPassword("password");

		UserLoginResponse loginResponse = restTemplate.postForObject(LOGIN_USER_URI, loginRequest,
				UserLoginResponse.class);

		assertEquals(Status.ERROR, loginResponse.getStatus());
		assertEquals("User not found, try with valid credentials", loginResponse.getErrorMessage());
	}

}
