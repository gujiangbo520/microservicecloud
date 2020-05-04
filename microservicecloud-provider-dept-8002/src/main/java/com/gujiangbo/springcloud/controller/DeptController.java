package com.gujiangbo.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gujiangbo.springcloud.entities.Dept;
import com.gujiangbo.springcloud.service.DeptService;

@RestController
public class DeptController {

    private Log debugLog = LogFactory.getLog(DeptController.class);

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/insertDept", method = RequestMethod.POST)
    public String insertDept(@RequestBody Dept dept) {
        debugLog.info("开始执行部门添加接口");
        debugLog.info("请求参数:" + JSONObject.toJSONString(dept));
        String resultMessage = null;
        try {
            boolean flag = deptService.insert(dept);
            if (flag) {
                resultMessage = "部门添加成功";
            } else {
                resultMessage = "部门添加失败";
            }
        } catch (Exception e) {
            resultMessage = "部门添加失败";
            debugLog.error("数据处理异常!", e);
        }
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/findDeptById", method = RequestMethod.POST)
    public String findDeptById(@RequestBody String id) {
        debugLog.info("根据部门ID查询部门");
        debugLog.info("请求参数:" + id);
        Dept dept = new Dept();
        try {
            dept = deptService.getDeptById(Long.valueOf(id));
        } catch (Exception e) {
            debugLog.error("数据处理异常!", e);
        }
        return JSONObject.toJSONString(dept);
    }

    @ResponseBody
    @RequestMapping(value = "/findDeptAll", method = RequestMethod.POST)
    public String findDeptAll() {
        debugLog.info("查询所有部门");
        List<Dept> list = deptService.list();
        String message = JSONObject.toJSONString(list);
        debugLog.info(message);
        return message;
    }

    /**
     * 服务发现
     *
     * @return
     */
    @RequestMapping(value = "/discovery", method = RequestMethod.POST)
    public Object discovery() {
        List<String> services = client.getServices();
        System.out.println("=======services=========" + services);
        List<ServiceInstance> svsList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance instance : svsList) {
            System.out.println("host:" + instance.getHost() + "\t" + "serviceId:" +
                    instance.getServiceId() + "\t" + "port:" + instance.getPort() + "\t" + "url:" + instance.getUri());
        }
        return this.client;
    }

}
