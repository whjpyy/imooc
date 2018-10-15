package com.chen.elasticsearch.demo;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * ClassName: MyConfig <br/>
 * Function: TODO <br/>
 * 
 * date: 2018年10月15日 10:31 <br/>
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
public class MyConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {
        InetSocketTransportAddress node = new InetSocketTransportAddress(
                InetAddress.getByName("www.mktest.com"), 9300
        );

        Settings settings = Settings.builder()
                .put("cluster.name", "wali")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
