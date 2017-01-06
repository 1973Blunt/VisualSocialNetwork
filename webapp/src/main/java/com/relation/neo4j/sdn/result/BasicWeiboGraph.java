package com.relation.neo4j.sdn.result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月1日
 */
public class BasicWeiboGraph {
	private ArrayList<BasicWeiboUserNode> nodes;
	private ArrayList<BasicFollowsEdge> edges;
	private Integer nCluster = 0;

	public BasicWeiboGraph() {
	}

	public void addNodes(List<BasicWeiboUserNode> nodes) {
		if (this.nodes == null)
			this.nodes = new ArrayList<BasicWeiboUserNode>();
		this.nodes.addAll(nodes);
	}
	
	public void addNode(BasicWeiboUserNode node) {
		if (this.nodes == null)
			this.nodes = new ArrayList<BasicWeiboUserNode>();
		this.nodes.add(node);
	}

	public void addEdges(List<BasicFollowsEdge> edges) {
		if (this.edges == null)
			this.edges = new ArrayList<BasicFollowsEdge>();
		this.edges.addAll(edges);
	}
	
	public void addEdge(BasicFollowsEdge edge) {
		if (this.edges == null)
			this.edges = new ArrayList<BasicFollowsEdge>();
		this.edges.add(edge);
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

	public Integer getnCluster() {
		return nCluster;
	}

	public void setnCluster(Integer nCluster) {
		this.nCluster = nCluster;
	}

}
