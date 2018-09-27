package com.hx.service;

import org.springframework.stereotype.Service;

/**
 * @author yangxinqiang
 * @create 2018-09-27 17:23
 */
@Service
public class HelloService {

    public String sayHello(String name){
        return "hello " + name;
    }
}
