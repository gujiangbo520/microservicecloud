package com.gujiangbo.springcloud.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gujiangbo.springcloud.entities.Dept;

@Repository
public interface DeptMapper {

	/**
	 * 添加部门
	 * 
	 * @param dept
	 * @return
	 */
	public boolean insertDept(Dept dept);

	/**
	 * 查找部门
	 * 
	 * @param id
	 * @return
	 */
	public Dept findDeptById(Long id);

	public List<Dept> findAllDept();
}
