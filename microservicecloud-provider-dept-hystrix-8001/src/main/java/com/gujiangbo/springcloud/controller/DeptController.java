package com.gujiangbo.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gujiangbo.springcloud.entities.Dept;
import com.gujiangbo.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 模拟熔断机制
     * @HystrixCommand(fallbackMethod = "processHystrix_Get")
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findDeptById", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public String findDeptById(@RequestBody String id) {
        debugLog.info("根据部门ID查询部门");
        debugLog.info("请求参数:" + id);
        Dept dept = new Dept();
        try {
            dept = deptService.getDeptById(Long.valueOf(id));
        } catch (Exception e) {
            debugLog.error("数据处理异常!", e);
        }
        if(null == dept){
            throw new RuntimeException("未找到对应信息");
        }
        return JSONObject.toJSONString(dept);
    }

    /**
     * hystrix 熔断机制模拟调用的方法
     * 只要在@HystrixCommand(fallbackMethod = "processHystrix_Get")注解方法上 出现异常等信息才调用
     * @param id
     * @return
     */
    public String processHystrix_Get(@RequestBody String id) {
        debugLog.info("开始调用熔断方法!");
        return "这是一个熔断方法！！！！";
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
