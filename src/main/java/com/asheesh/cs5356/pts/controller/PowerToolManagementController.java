package com.asheesh.cs5356.pts.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asheesh.cs5356.pts.entity.PowerTool;
import com.asheesh.cs5356.pts.entity.PowerTool.ToolStatus;
import com.asheesh.cs5356.pts.entity.User;
import com.asheesh.cs5356.pts.repository.PowerToolRepository;
import com.asheesh.cs5356.pts.repository.UserRepository;
import com.asheesh.cs5356.pts.request.AddPowerToolRequest;
import com.asheesh.cs5356.pts.request.GetPowerToolsRequest;
import com.asheesh.cs5356.pts.request.RemovePowerToolRequest;
import com.asheesh.cs5356.pts.request.UpdateToolStatusRequest;
import com.asheesh.cs5356.pts.response.AddPowerToolResponse;
import com.asheesh.cs5356.pts.response.GetPowerToolsResponse;
import com.asheesh.cs5356.pts.response.RemovePowerToolResponse;
import com.asheesh.cs5356.pts.response.UpdateToolStatusResponse;

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

		if (StringUtils.isEmpty(addPowerToolRequest.getLatitude())
				|| StringUtils.isEmpty(addPowerToolRequest.getLongitude())) {
			return (AddPowerToolResponse) addPowerToolResponse.createErrorResponse(
					"Tool location is required, kindly allow application to use location services and try again");
		}

		User user = userRepository.findByUserid(addPowerToolRequest.getUserId());

		if (user == null)
			return (AddPowerToolResponse) addPowerToolResponse
					.createErrorResponse("User not found, try with valid credentials");

		PowerTool powerTool = powerToolRepository
				.save(new PowerTool(addPowerToolRequest.getName(), addPowerToolRequest.getToolImageName(),
						addPowerToolRequest.getDescription(), addPowerToolRequest.getUserId(),
						addPowerToolRequest.getLatitude(), addPowerToolRequest.getLongitude()));

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

	@RequestMapping(value = "/updateToolStatus")
	public @ResponseBody UpdateToolStatusResponse updateMyPowerTool(
			@RequestBody UpdateToolStatusRequest updateToolStatusRequest) {
		UpdateToolStatusResponse updateToolStatusResponse = new UpdateToolStatusResponse();

		PowerTool powerTool = powerToolRepository.findByIdAndUserid(updateToolStatusRequest.getToolId(),
				updateToolStatusRequest.getUserId());

		if (powerTool == null)
			return (UpdateToolStatusResponse) updateToolStatusResponse.createErrorResponse("Power tool not found");

		if (updateToolStatusRequest.getStatus().equalsIgnoreCase(ToolStatus.AVAILABLE.getStatus())) {
			powerTool.markAvailable();

		} else if (updateToolStatusRequest.getStatus().equalsIgnoreCase(ToolStatus.UNAVAILABLE.getStatus())) {
			powerTool.markUnAvailable();

		} else {
			return (UpdateToolStatusResponse) updateToolStatusResponse
					.createErrorResponse("Invalid status update requested");
		}

		powerTool = powerToolRepository.save(powerTool);

		updateToolStatusResponse.powerTool = powerTool;

		return updateToolStatusResponse;
	}

	@RequestMapping(value = "/getMyTools")
	public @ResponseBody GetPowerToolsResponse getMyPowerTools(@RequestBody GetPowerToolsRequest getPowerToolsRequest) {
		GetPowerToolsResponse getPowerToolsResponse = new GetPowerToolsResponse();

		User user = userRepository.findByUserid(getPowerToolsRequest.getUserId());

		if (user == null)
			return (GetPowerToolsResponse) getPowerToolsResponse
					.createErrorResponse("User not found, try with valid credentials");

		List<PowerTool> powerTools = powerToolRepository.findByUserid(getPowerToolsRequest.getUserId());

		for (Iterator<PowerTool> iterator = powerTools.iterator(); iterator.hasNext();) {
			PowerTool powerTool = iterator.next();

			getPowerToolsResponse.setPowerTool(powerTool, user.getMobilenumber());
		}

		return getPowerToolsResponse;

	}

	@RequestMapping(value = "/getPublicTools")
	public @ResponseBody GetPowerToolsResponse getPublicPowerTools(
			@RequestBody GetPowerToolsRequest getPowerToolsRequest) {
		GetPowerToolsResponse getPowerToolsResponse = new GetPowerToolsResponse();

		User user = userRepository.findByUserid(getPowerToolsRequest.getUserId());

		if (user == null)
			return (GetPowerToolsResponse) getPowerToolsResponse
					.createErrorResponse("User not found, try with valid credentials");

		List<PowerTool> powerTools = powerToolRepository.findByUseridNot(getPowerToolsRequest.getUserId());

		for (Iterator<PowerTool> iterator = powerTools.iterator(); iterator.hasNext();) {
			PowerTool powerTool = iterator.next();

			User tempUser = userRepository.findByUserid(powerTool.getUserid());
			getPowerToolsResponse.setPowerTool(powerTool, tempUser.getMobilenumber());
		}

		return getPowerToolsResponse;

	}
}
