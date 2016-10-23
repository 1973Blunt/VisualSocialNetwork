package com.relation.neo4j;

import com.relation.neo4j.sdn.entity.WeiboUser;
import com.relation.neo4j.sdn.requestbody.NickName;
import com.relation.neo4j.sdn.result.FollowedUserList;
import com.relation.neo4j.sdn.result.UserInfo;
import com.relation.neo4j.sdn.service.WeiboUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        return "Hello Neo4j!";
    }

    @RequestMapping(value = "/getFollowedUsers", method = RequestMethod.POST)
    @ResponseBody
    public FollowedUserList getFollowedUsers(@RequestBody NickName nickName) {
        FollowedUserList followedUserList = new FollowedUserList();
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        System.out.println(nickName.getNickName());
        ArrayList<WeiboUser> followedUsers = (ArrayList<WeiboUser>)
                weiboUserService.getFollowedUsers(nickName.getNickName());
        if (followedUsers!=null&&followedUsers.size()>0) {
            System.out.println("查询到粉丝");
            for (WeiboUser weiboUserTemp : followedUsers) {
                UserInfo userInfo = new UserInfo();
                userInfo.setNickName(weiboUserTemp.getNickName());
                userInfo.setWb_usr_id(weiboUserTemp.getWb_usr_id() + "");
                userInfos.add(userInfo);
            }
            followedUserList.setUserInfos(userInfos);
        }
        return followedUserList;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public WeiboUser getUserInfo(@RequestBody NickName nickName) {
        WeiboUser weiboUser = weiboUserService.getUserInfo(nickName.getNickName());
        return weiboUser;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
