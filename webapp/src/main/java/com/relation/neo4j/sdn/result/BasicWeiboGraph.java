package com.relation.neo4j.sdn.result;

import java.util.ArrayList;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月1日
 */
public class BasicWeiboGraph {
	private ArrayList<BasicWeiboUserNode> nodes;
	private ArrayList<BasicFollowsEdge> edges;

	public BasicWeiboGraph() {
	}

	public void addNodes(ArrayList<BasicWeiboUserNode> nodes) {
		if (this.nodes == null)
			this.nodes = new ArrayList<BasicWeiboUserNode>();
		this.nodes.addAll(nodes);
	}

	public void addEdges(ArrayList<BasicFollowsEdge> edges) {
		if (this.edges == null)
			this.edges = new ArrayList<BasicFollowsEdge>();
		this.edges.addAll(edges);
	}

	public ArrayList<BasicWeiboUserNode> getNodes() {
		return nodes;
	}

	public ArrayList<BasicFollowsEdge> getEdges() {
		return edges;
	}

	public void setNodes(ArrayList<BasicWeiboUserNode> nodes) {
		this.nodes = nodes;
	}

	public void setEdges(ArrayList<BasicFollowsEdge> edges) {
		this.edges = edges;
	}

}
