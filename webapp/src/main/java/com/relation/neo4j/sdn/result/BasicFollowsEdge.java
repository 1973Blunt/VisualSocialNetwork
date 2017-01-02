package com.relation.neo4j.sdn.result;

import java.util.ArrayList;
import java.util.Set;

import com.relation.neo4j.sdn.entity.WeiboUser;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月1日
 */
public class BasicFollowsEdge {
	private Long source;
	private Long target;
	private String type = "FOLLOWS";

	public BasicFollowsEdge() {
	}

	/**
	 * 由WeiboUser的OUTGOING类型的关系集抽取关系列表，利用userIdSet过滤不相关的关系
	 * @param user
	 * @param userIdSet
	 * @return
	 */
	public static ArrayList<BasicFollowsEdge> getListByOutgoing(WeiboUser user, Set<Long> userIdSet ) {
		ArrayList<BasicFollowsEdge> edges = new ArrayList<BasicFollowsEdge>();
		if (user.getFollowingUsers() != null) {
			for (WeiboUser follow : user.getFollowingUsers()) {
				if(!userIdSet.contains(follow.getWb_usr_id()))
					continue;
				BasicFollowsEdge e = new BasicFollowsEdge();
				e.source = user.getWb_usr_id();
				e.target = follow.getWb_usr_id();
				edges.add(e);
			}
		}
		return edges;
	}

	/**
	 * 由WeiboUser的INCOMING类型的关系集抽取关系列表，利用userIdSet过滤不相关的关系
	 * @param user
	 * @param userIdSet
	 * @return
	 */
	public static ArrayList<BasicFollowsEdge> getListByIncoming(WeiboUser user, Set<Long> userIdSet ) {
		ArrayList<BasicFollowsEdge> edges = new ArrayList<BasicFollowsEdge>();
		if (user.getFollowedUsers() != null) {
			for (WeiboUser fan : user.getFollowedUsers()) {
				if(!userIdSet.contains(fan.getWb_usr_id()))
					continue;
				BasicFollowsEdge e = new BasicFollowsEdge();
				e.source = fan.getWb_usr_id();
				e.target = user.getWb_usr_id();
				edges.add(e);
			}
		}
		return edges;
	}

	public Long getSource() {
		return source;
	}

	public Long getTarget() {
		return target;
	}

	public String getType() {
		return type;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	public void setType(String type) {
		this.type = type;
	}

}
