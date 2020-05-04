package com.gujiangbo.springcloud.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gujiangbo.springcloud.dao.DeptDao;
import com.gujiangbo.springcloud.entities.Dept;
import com.gujiangbo.springcloud.mapper.DeptMapper;

@Repository
public class DeptDaoImpl implements DeptDao{
	
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public boolean insertDept(Dept dept) {
		return deptMapper.insertDept(dept);
	}

	@Override
	public Dept findDeptById(Long id) {
		return deptMapper.findDeptById(id);
	}

	@Override
	public List<Dept> findAllDept() {
		return deptMapper.findAllDept();
	}

}
