package com.gujiangbo.springcloud.application.controller;

import com.gujiangbo.springcloud.entities.Dept;
import com.gujiangbo.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gujiangbo
 */
@RestController
public class DeptController_Consumer {

    @Autowired
    private DeptClientService deptClientService;


    @RequestMapping(value = "/consumer/insertDept")
    public String add(Dept dept) {
        return deptClientService.insertDept(dept);
    }

    @RequestMapping(value = "/consumer/findDeptById")
    public String findDeptById(String id) {
        return deptClientService.findDeptById(id);
    }

    @RequestMapping(value = "/consumer/findDeptAll")
    public String findDeptAll() {
        return deptClientService.findDeptAll();
    }
}
