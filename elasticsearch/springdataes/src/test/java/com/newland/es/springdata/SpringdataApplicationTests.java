package com.newland.es.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
class SpringdataApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    //创建索引并增加映射配置
    @Test
    public void createIndex() {
        System.out.println("创建索引");
    }

    @Test
    public void deleteIndex() {
        //创建索引，系统初始化会自动创建索引
    }
}
