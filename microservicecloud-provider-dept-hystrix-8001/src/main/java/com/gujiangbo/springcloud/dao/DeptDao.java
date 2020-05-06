package com.gujiangbo.springcloud.dao;

import com.gujiangbo.springcloud.entities.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

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
