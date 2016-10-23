package com.relation.neo4j.sdn.result;

import java.util.ArrayList;

/**
 * Created by dxx on 2016/10/22.
 */
public class FollowedUserList {
    private ArrayList<UserInfo> userInfos;

    public FollowedUserList() {}

    public ArrayList<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(ArrayList<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
