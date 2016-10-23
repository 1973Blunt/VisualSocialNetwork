package com.relation.neo4j.sdn.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dxx on 2016/10/11.
 */
@NodeEntity(label="WeiboUser")
public class WeiboUser {
    public WeiboUser() {}

    public WeiboUser(long userId) {
        wb_usr_id = userId;
    }

    @GraphId
    Long id;

    @Property(name = "wb_usr_id")
    private Long wb_usr_id;

    @Property(name = "Num_Follows")
    private int num_follows;

    @Property(name = "Province")
    private String province;

    @Property(name = "URL")
    private String url;

    @Property(name = "Gender")
    private String gender;

    @Property(name = "Num_Fans")
    private int num_fans;

    @Property(name = "Num_Tweets")
    private int num_tweets;

    @Property(name = "NickName")
    private String nickName;

    @Property(name = "City")
    private String city;

    @Property(name = "Birthday")
    private String birthday;

    @Property(name = "Signature")
    private String signature;

    @Relationship(type = "FOLLOWS", direction = Relationship.OUTGOING)
    private Set<WeiboUser> followingUsers = new HashSet<>();

    public void follows(WeiboUser weiboUser) {
        followingUsers.add(weiboUser);
    }

    @Relationship(type = "FOLLOWS", direction = Relationship.INCOMING)
    private Set<WeiboUser> followedUsers = new HashSet<>();

    public Set<WeiboUser> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(Set<WeiboUser> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public Long getWb_usr_id() {
        return wb_usr_id;
    }

    public void setWb_usr_id(Long wb_usr_id) {
        this.wb_usr_id = wb_usr_id;
    }

    public int getNum_follows() {
        return num_follows;
    }

    public void setNum_follows(int num_follows) {
        this.num_follows = num_follows;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getNum_fans() {
        return num_fans;
    }

    public void setNum_fans(int num_fans) {
        this.num_fans = num_fans;
    }

    public int getNum_tweets() {
        return num_tweets;
    }

    public void setNum_tweets(int num_tweets) {
        this.num_tweets = num_tweets;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
