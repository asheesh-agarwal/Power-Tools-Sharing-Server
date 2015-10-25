package com.asheesh.cs5356.pts.repository;

import org.springframework.data.repository.CrudRepository;

import com.asheesh.cs5356.pts.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findById(String id);
	
	public User findByUserid(String userId);
	
	public User findByEmailid(String emailId);
}
