package com.asheesh.cs5356.pts.request;

public class AddPowerToolRequest {

	private String userId;
	private String name;
	private String toolImageName;
	private String description;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getToolImageName() {
		return toolImageName;
	}
	public void setToolImageName(String toolImageName) {
		this.toolImageName = toolImageName;
	}
}
