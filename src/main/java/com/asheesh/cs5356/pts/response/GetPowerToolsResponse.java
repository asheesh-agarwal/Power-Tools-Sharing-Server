package com.asheesh.cs5356.pts.response;

import java.util.List;

import com.asheesh.cs5356.pts.entity.PowerTool;

public class GetPowerToolsResponse extends Response {

	private List<PowerTool> powerTools;

	public GetPowerToolsResponse() {
	}

	public List<PowerTool> getPowerTools() {
		return powerTools;
	}

	public void setPowerTools(List<PowerTool> powerTools) {
		this.powerTools = powerTools;
	}
}
