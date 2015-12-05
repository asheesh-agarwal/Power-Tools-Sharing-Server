package com.asheesh.cs5356.pts.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pts_powertool")
public class PowerTool {

	@Id
	private String id;
	private String userid;
	private String toolname;
	private String toolimage;
	private String toolimagename;
	private String description;
	private String status;
	private String latitude;
	private String longitude;
	private Date creationdate;
	private Date updatedate;

	protected PowerTool() {
		// TODO Auto-generated constructor stub
	}

	public enum ToolStatus {
		AVAILABLE("AVAILABLE"), UNAVAILABLE("UNAVAILABLE");

		private String status;

		public String getStatus() {
			return status;
		}

		private ToolStatus(String toolStatus) {
			this.status = toolStatus;
		}
	}

	public PowerTool(String toolName, String toolImageName, String description, String userId, String latitude,
			String longitude) {
		this.id = UUID.randomUUID().toString();

		this.toolname = toolName;
		this.toolimagename = toolImageName;
		this.description = description;
		this.userid = userId;
		this.latitude = latitude;
		this.longitude = longitude;

		this.status = ToolStatus.AVAILABLE.getStatus();

		this.creationdate = Calendar.getInstance().getTime();
	}

	public void markAvailable() {
		this.status = ToolStatus.AVAILABLE.getStatus();
	}

	public void markUnAvailable() {
		this.status = ToolStatus.UNAVAILABLE.getStatus();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getToolname() {
		return toolname;
	}

	public void setToolname(String toolname) {
		this.toolname = toolname;
	}

	public String getToolimage() {
		return toolimage;
	}

	public void setToolimage(String toolimage) {
		this.toolimage = toolimage;
	}

	public String getToolimagename() {
		return toolimagename;
	}

	public void setToolimagename(String toolimagename) {
		this.toolimagename = toolimagename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
