package com.example.mybatisanddruid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 
 * ClassName: DruidConfig <br/>
 * Function: TODO <br/>
 * 
 * date: 2018年11月02日 16:37 <br/>
 * 
 * @author tianma
 * @version
 * @since JDK 1.8
 *
 * @modified By： <修改人> <br/>
 * @modified Date:<修改日期> <br/>
 * @desc：修改日志 <修改描述> <br/>
 */
@Configuration
public class DruidConfig {
    /**
     * 主要实现WEB监控的配置处理
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        // 进行druid监控的配置处理操作
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,10.188.57.101"); // 白名单
        servletRegistrationBean.addInitParameter("deny", "10.188.57.102"); // 黑名单
        servletRegistrationBean.addInitParameter("loginUsername", "admin"); //用户名
        servletRegistrationBean.addInitParameter("loginPassword", "admin"); // 密码
        servletRegistrationBean.addInitParameter("resetEnable", "false"); // 是否可以重置数据源
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());

        filterRegistrationBean.addUrlPatterns("/*"); // 所有请求进行监控处理
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
}
