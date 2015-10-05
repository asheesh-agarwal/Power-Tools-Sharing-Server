package com.asheesh.cs5356.pts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.asheesh.cs5356.pts.entity.PowerTool;

public interface PowerToolRepository extends CrudRepository<PowerTool, String> {

	public PowerTool findById(String id);

	public List<PowerTool> findByUserid(String userId);
	
	public PowerTool findByIdAndUserid(String id, String userId);
}
