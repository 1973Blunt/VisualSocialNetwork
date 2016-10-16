package com.relation.neo4j.service;

import com.relation.neo4j.Application;
import com.relation.neo4j.sdn.service.WeiboUserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dxx on 2016/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WeiboUserServiceTest extends TestCase {
    @Autowired
    private WeiboUserService weiboUserService;

    @Test
    public void testInitdata() throws Exception {
        weiboUserService.initdata();
    }

}