package com.immoc.springboot.controller;

import com.immoc.springboot.dao.WomenRepository;
import com.immoc.springboot.entity.Girl;
import com.immoc.springboot.entity.Women;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private WomenRepository womenRepository;

    @RequestMapping(value = {"/hello", "hi"}, method = RequestMethod.GET)
    public String say(Integer id){
        String result = "Hello Spring Boot!" + "<br />";
        result += "cupSize: " + cupSize + "<br />";
        result += "age: " + age + "<br />";
        result += "content: " + content + "<br />";
        result += "girl: " + girl + "<br />";
        result += "id:" + id + "<br />";
        return result;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(){
        return "this is a post method";
    }

    @GetMapping("/getMapping")
    public String getMapping(){
       return "getMapping";
    }

    @GetMapping(value = "/women")
    public List<Women> womenList(){
        return womenRepository.findAll();
    }

    @PostMapping(value = "women/add")
    public Women girlAdd(Integer age, String cupSize){
        Women women = new Women();
        women.setAge(age);
        women.setCupSize(cupSize);
        return womenRepository.save(women);
    }

    @GetMapping("/women/{id}")
    public Women get(@PathVariable("id") Integer id){
        Optional<Women> women = womenRepository.findById(id);
        System.out.println(women.get());
//        return womenRepository.findOne(id);
        return women.get();
    }

    @PutMapping("/women/{id}")
    public Women update(@PathVariable("id") Integer id, Integer age, String cupSize){
        Women women = new Women();
        women.setAge(age);
        women.setCupSize(cupSize);
        women.setId(id);
        return womenRepository.save(women);
    }

    @DeleteMapping("/women/{id}")
    public void delete(@PathVariable("id") Integer id){
        Women women = new Women();
        women.setId(id);
        womenRepository.delete(women);
    }

    @GetMapping("/women/age/{age}")
    public List<Women> queryByAge(@PathVariable("age") Integer age){
        return womenRepository.findByAge(age);
    }
}
