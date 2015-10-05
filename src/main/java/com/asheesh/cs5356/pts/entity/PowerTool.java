package com.asheesh.cs5356.pts.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="pts_powertool")
public class PowerTool {

	@Id
	private String id;
	private String userid;
	private String toolname;
	private String toolimage;
	private String description;
	private Date creationdate;
	private Date updatedate;

	protected PowerTool() {
		// TODO Auto-generated constructor stub
	}

	public PowerTool(String toolName, String toolimage, String description, String userId) {
		this.id = UUID.randomUUID().toString();

		this.toolname = toolName;
		this.toolimage = toolimage;
		this.description = description;
		this.userid = userId;
		
		this.creationdate = Calendar.getInstance().getTime();
	}

	public String getToolName() {
		return toolname;
	}

	public void setToolName(String toolName) {
		this.toolname = toolName;
	}

	public String getToolImage() {
		return toolimage;
	}

	public void setToolImage(String image) {
		this.toolimage = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
}
