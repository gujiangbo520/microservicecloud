package com.gujiangbo.springcloud.application.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.gujiangbo.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {

    private Log logdebug = LogFactory.getLog(DeptController_Consumer.class);

    private static final String REST_URL_PREFIX= "http://localhost:8001/";

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/consumer/addDept")
    public String add(Dept dept) {
        logdebug.info("请求参数:" + JSONObject.toJSONString(dept));
        return restTemplate.postForObject(REST_URL_PREFIX +"insertDept", dept, String.class);
    }

    @ResponseBody
    @RequestMapping("/consumer/findDept")
    public String findDept(String id) {
        return restTemplate.postForObject(REST_URL_PREFIX+"findDeptById", id, String.class);
    }

    @ResponseBody
    @RequestMapping("/consumer/findAll")
    public String findAll() {
        return restTemplate.postForObject(REST_URL_PREFIX+"findAll", null, String.class);
    }

    @ResponseBody
    @RequestMapping("/consumer/discovery")
    public Object discovery() {
        return restTemplate.postForObject(REST_URL_PREFIX+"discovery", null, Object.class);
    }



}
