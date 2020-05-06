package com.gujiangbo.springcloud.mapper;

import com.gujiangbo.springcloud.entities.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

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
