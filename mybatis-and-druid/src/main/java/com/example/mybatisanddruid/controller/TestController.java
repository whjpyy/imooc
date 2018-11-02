package com.example.mybatisanddruid.controller;

import com.example.mybatisanddruid.mapper.TagsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 
 * ClassName: TestController <br/>
 * Function: TODO <br/>
 * 
 * date: 2018年11月02日 16:03 <br/>
 * 
 * @author tianma
 * @version
 * @since JDK 1.8
 *
 * @modified By： <修改人> <br/>
 * @modified Date:<修改日期> <br/>
 * @desc：修改日志 <修改描述> <br/>
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private TagsCategoryMapper categoryMapper = null;


    @RequestMapping("/one")
    @ResponseBody
    public Map testdb(){
        return categoryMapper.findObjectById(21);
    }
}
