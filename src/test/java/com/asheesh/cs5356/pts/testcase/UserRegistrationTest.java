package com.asheesh.cs5356.pts.testcase;

import org.springframework.web.client.RestTemplate;

import com.asheesh.cs5356.pts.request.UserRegistrationRequest;
import com.asheesh.cs5356.pts.request.UserTerminationRequest;
import com.asheesh.cs5356.pts.response.Response.Status;
import com.asheesh.cs5356.pts.response.UserRegistrationResponse;
import com.asheesh.cs5356.pts.response.UserTerminationResponse;

import junit.framework.TestCase;

public class UserRegistrationTest extends TestCase {

	private static final String REGISTER_USER_URI = "http://localhost:8080/registerUser";
	private static final String TERMINATE_USER_URI = "http://localhost:8080/terminateUser";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		terminateUser();
	}

	private void terminateUser() {
		UserTerminationRequest terminationRequest = new UserTerminationRequest();

		terminationRequest.setEmailId("first.last@gmail.com");
		terminationRequest.setPassword("password");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(TERMINATE_USER_URI, terminationRequest, UserTerminationResponse.class);
	}

	public void testSuccessfulUserRegistration() {
		RestTemplate restTemplate = new RestTemplate();

		UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
		registrationRequest.setFirstName("First Name");
		registrationRequest.setLastName("Last Name");
		registrationRequest.setEmailId("first.last@gmail.com");
		registrationRequest.setPassword("password");
		registrationRequest.setConfirmPassword("password");

		UserRegistrationResponse registrationResponse = restTemplate.postForObject(REGISTER_USER_URI,
				registrationRequest, UserRegistrationResponse.class);

		assertEquals(Status.SUCCESS, registrationResponse.getStatus());
		assertNotNull("User registration failed, userId is null", registrationResponse.getUserId());
	}

	public void testDuplicateUserRegistration() {
		RestTemplate restTemplate = new RestTemplate();

		UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
		registrationRequest.setFirstName("First Name");
		registrationRequest.setLastName("Last Name");
		registrationRequest.setEmailId("first.last@gmail.com");
		registrationRequest.setPassword("password");
		registrationRequest.setConfirmPassword("password");

		UserRegistrationResponse registrationResponse = restTemplate.postForObject(REGISTER_USER_URI,
				registrationRequest, UserRegistrationResponse.class);

		assertEquals(Status.SUCCESS, registrationResponse.getStatus());
		assertNotNull("User registration failed, userId is null", registrationResponse.getUserId());

		registrationResponse = restTemplate.postForObject(REGISTER_USER_URI, registrationRequest,
				UserRegistrationResponse.class);

		assertEquals(Status.ERROR, registrationResponse.getStatus());
		assertNotNull("Error message is null", registrationResponse.getErrorMessage());
	}

	public void testUserRegistrationWithEmptyEmailId() {
		RestTemplate restTemplate = new RestTemplate();

		UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
		registrationRequest.setFirstName("First Name");
		registrationRequest.setLastName("Last Name");
		registrationRequest.setEmailId("");
		registrationRequest.setPassword("password");
		registrationRequest.setConfirmPassword("password");

		UserRegistrationResponse registrationResponse = restTemplate.postForObject(REGISTER_USER_URI,
				registrationRequest, UserRegistrationResponse.class);

		assertEquals(Status.ERROR, registrationResponse.getStatus());
		assertEquals("Email Id cannot be empty", registrationResponse.getErrorMessage());
	}

	public void testUserRegistrationWithDifferentConfirmPassword() {
		RestTemplate restTemplate = new RestTemplate();

		UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
		registrationRequest.setFirstName("First Name");
		registrationRequest.setLastName("Last Name");
		registrationRequest.setEmailId("first.last@gmail.com");
		registrationRequest.setPassword("password");
		registrationRequest.setConfirmPassword("password1");

		UserRegistrationResponse registrationResponse = restTemplate.postForObject(REGISTER_USER_URI,
				registrationRequest, UserRegistrationResponse.class);

		assertEquals(Status.ERROR, registrationResponse.getStatus());
		assertEquals("Both password and confirm password should have same value",
				registrationResponse.getErrorMessage());
	}

}
