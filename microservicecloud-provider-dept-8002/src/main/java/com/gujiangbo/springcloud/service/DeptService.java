package com.gujiangbo.springcloud.service;

import java.util.List;

import com.gujiangbo.springcloud.entities.Dept;

public interface DeptService {
	
	public boolean insert(Dept dept);
	
	public Dept getDeptById(Long id);
	
	public List<Dept> list();

}
