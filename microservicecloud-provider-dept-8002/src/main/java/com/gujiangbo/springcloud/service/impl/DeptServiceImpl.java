package com.gujiangbo.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujiangbo.springcloud.dao.DeptDao;
import com.gujiangbo.springcloud.entities.Dept;
import com.gujiangbo.springcloud.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao dao;

	@Override
	public boolean insert(Dept dept) {
		return dao.insertDept(dept);
	}

	@Override
	public Dept getDeptById(Long id) {
		return dao.findDeptById(id);
	}

	@Override
	public List<Dept> list() {
		return dao.findAllDept();
	}

}
