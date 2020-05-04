package com.gujiangbo.springcloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.gujiangbo.springcloud.entities.Dept;

@Service
public interface DeptDao {
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	public boolean insertDept(Dept dept);
	/**
	 * 查找部门
	 * @param id
	 * @return
	 */
	public Dept findDeptById(Long id);
	
	public List<Dept> findAllDept();
	
	
}
