package com.relation.neo4j.sdn.service;

import com.relation.neo4j.sdn.entity.WeiboUser;
import com.relation.neo4j.sdn.repository.WeiboUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dxx on 2016/10/14.
 */
@Service
public class WeiboUserService {
    @Autowired
    private WeiboUserRepository weiboUserRepository;

    @Transactional
    public void initdata() {
        WeiboUser weiboUser1 = new WeiboUser(1234567890L);
//        weiboUser1.setCity("成都");
//        weiboUser1.setBirthday("1995-03-13");
//        weiboUser1.setGender("男");
//        weiboUser1.setNickName("nihao");
//        weiboUser1.setNum_fans(12);
//        weiboUser1.setNum_follows(12);
//        weiboUser1.setNum_tweets(12);
//        weiboUser1.setProvince("四川");
//        weiboUser1.setUrl("www.baidu.com");
        WeiboUser weiboUser2 = new WeiboUser(1203456789L);
        WeiboUser weiboUser3 = new WeiboUser(1023456789L);
        weiboUser1.follows(weiboUser2);
        weiboUser1.follows(weiboUser3);
//        weiboUser1.setFollowingUsers(Lists.newArrayList(weiboUser2, weiboUser3));

        weiboUserRepository.save(weiboUser1);
        Long testId = 5871897095L;
        System.out.println(weiboUserRepository.exists(testId));

    }

}
