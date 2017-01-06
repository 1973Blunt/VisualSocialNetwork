package com.relation.neo4j.sdn.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月5日
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "FOLLOWS")
public class Follow {
	@GraphId
	Long id;

	@StartNode
	private WeiboUser start;
	@EndNode
	private WeiboUser end;

	public Long getId() {
		return id;
	}

	public WeiboUser getStart() {
		return start;
	}

	public WeiboUser getEnd() {
		return end;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStart(WeiboUser start) {
		this.start = start;
	}

	public void setEnd(WeiboUser end) {
		this.end = end;
	}

}
