package com.gujiangbo.springcloud.controller;

import com.gujiangbo.springcloud.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class HelloController {

    @Autowired
    private Person person;

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "hello world!";
    }

    @RequestMapping("/person")
    public String person(){
        return person.toString();
    }
}
