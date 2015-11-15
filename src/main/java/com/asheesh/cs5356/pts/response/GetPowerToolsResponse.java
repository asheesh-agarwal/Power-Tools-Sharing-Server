package com.asheesh.cs5356.pts.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asheesh.cs5356.pts.entity.PowerTool;

public class GetPowerToolsResponse extends Response {

	private List<PowerToolInfoForClient> powerTools = new ArrayList<PowerToolInfoForClient>();

	public GetPowerToolsResponse() {
	}

	public List<PowerToolInfoForClient> getPowerTools() {
		return powerTools;
	}

	public void setPowerTool(PowerTool powerTool, String mobileNumber) {
		PowerToolInfoForClient powerToolInfoForClient = new PowerToolInfoForClient()
				.getPowerToolInfoForClient(powerTool);
		
		powerToolInfoForClient.mobilenumber = mobileNumber;
		
		this.powerTools.add(powerToolInfoForClient);
	}

	private class PowerToolInfoForClient {
		public String id;
		public String userid;
		public String mobilenumber;
		public String toolname;
		public String toolimage;
		public String toolimagename;
		public String description;
		public String status;
		public Date creationdate;

		public PowerToolInfoForClient getPowerToolInfoForClient(PowerTool powerTool) {
			this.id = powerTool.getId();
			this.userid = powerTool.getUserid();
			this.toolname = powerTool.getToolname();
			this.toolimage = powerTool.getToolimage();
			this.toolimagename = powerTool.getToolimagename();
			this.description = powerTool.getDescription();
			this.status = powerTool.getStatus();
			this.creationdate = powerTool.getCreationdate();

			return this;
		}
	}
}
