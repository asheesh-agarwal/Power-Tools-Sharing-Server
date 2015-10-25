package com.asheesh.cs5356.pts.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.springframework.web.client.RestTemplate;

import com.asheesh.cs5356.pts.request.AddPowerToolRequest;
import com.asheesh.cs5356.pts.request.RemovePowerToolRequest;
import com.asheesh.cs5356.pts.request.UserRegistrationRequest;
import com.asheesh.cs5356.pts.request.UserTerminationRequest;
import com.asheesh.cs5356.pts.response.AddPowerToolResponse;
import com.asheesh.cs5356.pts.response.RemovePowerToolResponse;
import com.asheesh.cs5356.pts.response.Response.Status;
import com.asheesh.cs5356.pts.response.UserRegistrationResponse;
import com.asheesh.cs5356.pts.response.UserTerminationResponse;

import junit.framework.TestCase;

public class PowerToolManagementTest extends TestCase {

	private static final String REGISTER_USER_URI = "http://localhost:8080/registerUser";
	private static final String TERMINATE_USER_URI = "http://localhost:8080/terminateUser";
	private static final String ADD_TOOL_USER_URI = "http://localhost:8080/addTool";
	private static final String REMOVE_TOOL_USER_URI = "http://localhost:8080/removeTool";

	private String toolId;
	private String userId;

	public PowerToolManagementTest() {
	}

	public PowerToolManagementTest(String name) {
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

		if (toolId != null && toolId.length() > 0)
			removeTool();

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

		UserRegistrationResponse userRegistrationResponse = restTemplate.postForObject(REGISTER_USER_URI,
				registrationRequest, UserRegistrationResponse.class);

		userId = userRegistrationResponse.getUserId();
	}

	private void terminateUser() {
		UserTerminationRequest terminationRequest = new UserTerminationRequest();

		terminationRequest.setEmailId("first.last@gmail.com");
		terminationRequest.setPassword("password");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(TERMINATE_USER_URI, terminationRequest, UserTerminationResponse.class);
	}

	private void removeTool() {
		RemovePowerToolRequest removePowerToolRequest = new RemovePowerToolRequest();

		removePowerToolRequest.setToolId(toolId);
		removePowerToolRequest.setUserId(userId);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(REMOVE_TOOL_USER_URI, removePowerToolRequest, RemovePowerToolResponse.class);

	}

	public void testAddPowerToolForValidUserWithValidData() {
		AddPowerToolRequest addPowerToolRequest = new AddPowerToolRequest();

		addPowerToolRequest.setUserId(userId);
		addPowerToolRequest.setName("Nail Gun");
		addPowerToolRequest.setToolImageName("Nail_Gun.jpg");
		addPowerToolRequest.setDescription("Gun used to pin different nails anywhere");

		RestTemplate restTemplate = new RestTemplate();
		AddPowerToolResponse addPowerToolResponse = restTemplate.postForObject(ADD_TOOL_USER_URI, addPowerToolRequest,
				AddPowerToolResponse.class);

		toolId = addPowerToolResponse.getToolId();

		assertEquals(Status.SUCCESS, addPowerToolResponse.getStatus());
		assertNotNull("Add tool request failed, tool id is null", addPowerToolResponse.getToolId());
	}

	private String getImageString() {
		try {
			File file = new File(
					"/Users/asheeshagarwal/Documents/java_workspace/power_tools_sharing/power-tools-sharing/src/test/java/com/asheesh/cs5356/pts/testcase/sample-image.png");
			byte[] array = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(array);
			fis.close();

			return Base64.getEncoder().encodeToString(array);

		} catch (FileNotFoundException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
	}

	public void testAddPowerToolForValidUserWithInvalidData() {
		AddPowerToolRequest addPowerToolRequest = new AddPowerToolRequest();

		addPowerToolRequest.setUserId(userId);
		addPowerToolRequest.setName("");
		addPowerToolRequest.setToolImageName("Nail_Gun.jpg");
		addPowerToolRequest.setDescription("Gun used to pin different nails anywhere");

		RestTemplate restTemplate = new RestTemplate();
		AddPowerToolResponse addPowerToolResponse = restTemplate.postForObject(ADD_TOOL_USER_URI, addPowerToolRequest,
				AddPowerToolResponse.class);

		toolId = addPowerToolResponse.getToolId();

		assertEquals(Status.ERROR, addPowerToolResponse.getStatus());
		assertEquals("Tool name is required in request", addPowerToolResponse.getErrorMessage());
	}

	public void testAddPowerToolForInvalidUserWithValidData() {

		AddPowerToolRequest addPowerToolRequest = new AddPowerToolRequest();

		addPowerToolRequest.setUserId("");
		addPowerToolRequest.setName("Nail Gun");
		addPowerToolRequest.setToolImageName("Nail_Gun.jpg");
		addPowerToolRequest.setDescription("Gun used to pin different nails anywhere");

		RestTemplate restTemplate = new RestTemplate();
		AddPowerToolResponse addPowerToolResponse = restTemplate.postForObject(ADD_TOOL_USER_URI, addPowerToolRequest,
				AddPowerToolResponse.class);

		toolId = addPowerToolResponse.getToolId();

		assertEquals(Status.ERROR, addPowerToolResponse.getStatus());
		assertEquals("User Id is required in request", addPowerToolResponse.getErrorMessage());
	}

	public static void main(String[] args) {
		System.out.println(new PowerToolManagementTest().getImageString());
	}
}
