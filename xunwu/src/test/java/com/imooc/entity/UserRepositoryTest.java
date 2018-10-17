package com.imooc.entity;

import com.imooc.ApplicationTests;
import com.imooc.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * ClassName: UserRepositoryTest <br/>
 * Function: TODO <br/>
 * 
 * date: 2018年10月16日 16:40 <br/>
 * 
 * @author tianma
 * @version
 * @since JDK 1.8
 *
 * @modified By： <修改人> <br/>
 * @modified Date:<修改日期> <br/>
 * @desc：修改日志 <修改描述> <br/>
 */
public class UserRepositoryTest extends ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){
        User user = userRepository.findOne(1L);
        Assert.assertEquals("wali", user.getName());
    }
}
