package com.relation.neo4j.sdn.result;

/**
 * Created by dxx on 2016/10/22.
 */
public class UserInfo {
    private String nickName;
    private String wb_usr_id;

    public UserInfo() {}

    public UserInfo(String nickName, String wb_usr_id) {
        this.nickName = nickName;
        this.wb_usr_id = wb_usr_id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWb_usr_id() {
        return wb_usr_id;
    }

    public void setWb_usr_id(String wb_usr_id) {
        this.wb_usr_id = wb_usr_id;
    }
}
