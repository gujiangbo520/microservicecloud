package com.gujiangbo.springcloud.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Value("${random.value}")
    private String name;

    @Value("${random.int[15,30]}")
    private int age;
    @Value("${random.value}")
    private String address;

    @Value("${random.long}")
    private long loval;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getLoval() {
        return loval;
    }

    public void setLoval(long loval) {
        this.loval = loval;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", loval=" + loval +
                '}';
    }
}
