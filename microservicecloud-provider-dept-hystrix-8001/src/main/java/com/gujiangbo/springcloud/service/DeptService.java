package com.gujiangbo.springcloud.service;

import com.gujiangbo.springcloud.entities.Dept;

import java.util.List;

public interface DeptService {
	
	public boolean insert(Dept dept);
	
	public Dept getDeptById(Long id);
	
	public List<Dept> list();

}
