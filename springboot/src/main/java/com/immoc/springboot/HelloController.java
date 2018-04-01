package com.immoc.springboot;

import com.immoc.springboot.entity.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private int age;

    @Value("${content}")
    private String content;

    @Autowired
    private Girl girl;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
        String result = "Hello Spring Boot!" + "<br />";
        result += "cupSize: " + cupSize + "<br />";
        result += "age: " + age + "<br />";
        result += "content: " + content + "<br />";
        result += "girl: " + girl + "<br />";
        return result;
    }

}
