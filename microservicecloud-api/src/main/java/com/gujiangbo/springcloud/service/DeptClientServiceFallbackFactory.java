package com.gujiangbo.springcloud.service;

import com.alibaba.fastjson.JSONObject;
import com.gujiangbo.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author gujiangbo
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable cause) {

        return new DeptClientService() {

            @Override
            public String insertDept(Dept dept) {
                return null;
            }

            @Override
            public String findDeptById(String id) {
                Dept dept = new Dept();
                dept.setDeptno(Long.valueOf(id));
                dept.setDname("该ID：" + id + "没有对应的信息，Consumer客户端提供的信息，此服务Provider已关闭");
                dept.setDb_source("no this database in mysql");
                return JSONObject.toJSONString(dept);
            }

            @Override
            public String findDeptAll() {
                return null;
            }
        };
    }
}
