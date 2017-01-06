package com.relation.neo4j.sdn.result;

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
	
	public BasicFollowsEdge(Long source, Long target) {
		this.source = source;
		this.target = target;
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
