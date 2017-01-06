package com.relation.neo4j.sdn.result;

import java.util.ArrayList;

import com.relation.neo4j.sdn.entity.WeiboUser;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月1日
 */
public class BasicWeiboUserNode {
	private Long id;
	private String nickName;
	private Integer cluster = 0;

	private String type = "WeiboUser";

	public BasicWeiboUserNode() {
	}

	public BasicWeiboUserNode(WeiboUser weiboUser) {
		if(weiboUser == null)
			return;
		id = weiboUser.getWb_usr_id();
		nickName = weiboUser.getNickName();
	}

	public static ArrayList<BasicWeiboUserNode> getListByWeiboUser(ArrayList<WeiboUser> weiboUsers) {
		ArrayList<BasicWeiboUserNode> list = new ArrayList<BasicWeiboUserNode>();
		if (weiboUsers != null) {
			for (WeiboUser usr : weiboUsers) {
				list.add(new BasicWeiboUserNode(usr));
			}
		}
		return list;
	}

	public Integer getCluster() {
		return cluster;
	}

	public void setCluster(Integer cluster) {
		this.cluster = cluster;
	}

	public String getNickName() {
		return nickName;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

}
