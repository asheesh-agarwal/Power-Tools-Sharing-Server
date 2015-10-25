package com.asheesh.cs5356.pts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asheesh.cs5356.pts.entity.PowerTool;
import com.asheesh.cs5356.pts.entity.User;
import com.asheesh.cs5356.pts.repository.PowerToolRepository;
import com.asheesh.cs5356.pts.repository.UserRepository;
import com.asheesh.cs5356.pts.request.AddPowerToolRequest;
import com.asheesh.cs5356.pts.request.GetPowerToolsRequest;
import com.asheesh.cs5356.pts.request.RemovePowerToolRequest;
import com.asheesh.cs5356.pts.response.AddPowerToolResponse;
import com.asheesh.cs5356.pts.response.GetPowerToolsResponse;
import com.asheesh.cs5356.pts.response.RemovePowerToolResponse;

@RestController
public class PowerToolManagementController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PowerToolRepository powerToolRepository;

	@RequestMapping(value = "/addTool")
	public @ResponseBody AddPowerToolResponse addMyPowerTool(@RequestBody AddPowerToolRequest addPowerToolRequest) {
		AddPowerToolResponse addPowerToolResponse = new AddPowerToolResponse();

		if (StringUtils.isEmpty(addPowerToolRequest.getUserId()))
			return (AddPowerToolResponse) addPowerToolResponse.createErrorResponse("User Id is required in request");

		if (StringUtils.isEmpty(addPowerToolRequest.getName()))
			return (AddPowerToolResponse) addPowerToolResponse.createErrorResponse("Tool name is required in request");

		if (StringUtils.isEmpty(addPowerToolRequest.getToolImageName())) {
			return (AddPowerToolResponse) addPowerToolResponse
					.createErrorResponse("Tool image name is required in request");
		}

		User user = userRepository.findByUserid(addPowerToolRequest.getUserId());

		if (user == null)
			return (AddPowerToolResponse) addPowerToolResponse
					.createErrorResponse("User not found, try with valid credentials");

		PowerTool powerTool = powerToolRepository
				.save(new PowerTool(addPowerToolRequest.getName(), addPowerToolRequest.getToolImageName(),
						addPowerToolRequest.getDescription(), addPowerToolRequest.getUserId()));

		addPowerToolResponse.setToolId(powerTool.getId());

		return addPowerToolResponse;
	}

	@RequestMapping(value = "/removeTool")
	public @ResponseBody RemovePowerToolResponse removeMyPowerTool(
			@RequestBody RemovePowerToolRequest removePowerToolRequest) {
		RemovePowerToolResponse removePowerToolResponse = new RemovePowerToolResponse();

		PowerTool powerTool = powerToolRepository.findByIdAndUserid(removePowerToolRequest.getToolId(),
				removePowerToolRequest.getUserId());

		if (powerTool == null)
			return (RemovePowerToolResponse) removePowerToolResponse.createErrorResponse("Power tool not found");

		powerToolRepository.delete(powerTool);

		return removePowerToolResponse;
	}

	@RequestMapping(value = "/updateTool")
	public void updateMyPowerTool() {

	}

	@RequestMapping(value = "/getTools")
	public @ResponseBody GetPowerToolsResponse getMyPowerTools(@RequestBody GetPowerToolsRequest getPowerToolsRequest) {
		GetPowerToolsResponse getPowerToolsResponse = new GetPowerToolsResponse();

		User user = userRepository.findByUserid(getPowerToolsRequest.getUserId());

		if (user == null)
			return (GetPowerToolsResponse) getPowerToolsResponse
					.createErrorResponse("User not found, try with valid credentials");

		List<PowerTool> powerTools = powerToolRepository.findByUserid(getPowerToolsRequest.getUserId());

		getPowerToolsResponse.setPowerTools(powerTools);

		return getPowerToolsResponse;

	}
}
