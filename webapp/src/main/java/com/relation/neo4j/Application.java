package com.relation.neo4j;

import com.relation.neo4j.sdn.service.WeiboUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dxx on 2016/10/11.
 */
@SpringBootApplication
@RestController
public class Application extends SpringBootServletInitializer{
    @Autowired
    WeiboUserService weiboUserService;

    @RequestMapping("/")
    public String greeting() {
        weiboUserService.initdata();
        return "Hello world!";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
