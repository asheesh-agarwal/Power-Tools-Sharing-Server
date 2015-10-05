package com.asheesh.cs5356.pts.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="pts_user")
public class User {

	@Id
	private String id;
	private String userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private byte[] password;
	private String status;
	private Date creationDate;
	private Date updateDate;
	
	protected User(){
		
	}
	
	public User(String firstName, String lastName, String emailId, byte[] password) {
		this.id = UUID.randomUUID().toString();
		this.userId = UUID.randomUUID().toString();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		
		this.status = "ACTIVE";
		this.creationDate = Calendar.getInstance().getTime();
	}

	public String getId() {
		return id;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
