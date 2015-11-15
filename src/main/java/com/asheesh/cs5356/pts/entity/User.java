package com.asheesh.cs5356.pts.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pts_user")
public class User {

	@Id
	private String id;
	private String userid;
	private String firstname;
	private String lastname;
	private String mobilenumber;
	private String emailid;
	private byte[] password;
	private String status;
	private Date creationdate;
	private Date updatedate;

	protected User() {

	}

	public User(String firstName, String lastName, String mobileNumber, String emailId, byte[] password) {
		this.id = UUID.randomUUID().toString();
		this.userid = UUID.randomUUID().toString();

		this.firstname = firstName;
		this.lastname = lastName;
		this.mobilenumber = mobileNumber;
		this.emailid = emailId;
		this.password = password;

		this.status = "ACTIVE";
		this.creationdate = Calendar.getInstance().getTime();
	}

	public String getId() {
		return id;
	}

	public String getUserid() {
		return userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
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
