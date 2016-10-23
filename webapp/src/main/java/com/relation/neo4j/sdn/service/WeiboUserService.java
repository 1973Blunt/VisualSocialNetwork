package com.relation.neo4j.sdn.service;

import com.relation.neo4j.sdn.entity.WeiboUser;
import com.relation.neo4j.sdn.repository.WeiboUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        WeiboUser weiboUser2 = new WeiboUser(1203456789L);
        WeiboUser weiboUser3 = new WeiboUser(1023456789L);
        weiboUser1.follows(weiboUser2);
        weiboUser1.follows(weiboUser3);

        weiboUserRepository.save(weiboUser1);
        Long testId = 5871897095L;
        System.out.println(weiboUserRepository.exists(testId));
    }

    @Transactional
    public List<WeiboUser> getFollowedUsers(String nickName) {
        return weiboUserRepository.getFansInfo(nickName);
    }

    @Transactional
    public WeiboUser getUserInfo(String nickName) {
        return weiboUserRepository.getUserByName(nickName);
    }

}
