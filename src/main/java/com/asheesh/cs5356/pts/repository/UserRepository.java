package com.asheesh.cs5356.pts.repository;

import org.springframework.data.repository.CrudRepository;

import com.asheesh.cs5356.pts.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findById(String id);
	
	public User findByUserId(String userId);
	
	public User findByEmailId(String emailId);
}
