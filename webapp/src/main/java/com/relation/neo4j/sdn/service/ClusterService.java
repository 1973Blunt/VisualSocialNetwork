package com.relation.neo4j.sdn.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.relation.algorithm.clustering.Clustering;
import com.relation.algorithm.clustering.ModularityOptimizer;
import com.relation.neo4j.sdn.result.BasicFollowsEdge;
import com.relation.neo4j.sdn.result.BasicWeiboGraph;
import com.relation.neo4j.sdn.result.BasicWeiboUserNode;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月4日
 */
@Service
public class ClusterService {
	
	@Transactional
	public void cluster(BasicWeiboGraph graph) {
		if (graph == null || graph.getNodes().size() <= 0)
			return;

		int nodeNum = graph.getNodes().size();

		Map<Long, Integer> map = new HashMap<Long, Integer>(nodeNum);
		int i = 0;
		for (BasicWeiboUserNode nd : graph.getNodes())
			map.put(nd.getId(), i++);

		int src, tar;
		Set<Edge> set = new HashSet<Edge>();
		for (BasicFollowsEdge e : graph.getEdges()) {
			src = map.get(e.getSource());
			tar = map.get(e.getTarget());
			if (src <= tar)
				set.add(new Edge(src, tar));
			else
				set.add(new Edge(tar, src));
		}
		Edge[] undirectEdge = new Edge[set.size()];
		set.toArray(undirectEdge);
        Arrays.sort(undirectEdge, new Comparator<Object>() {    
            public int compare(Object o1, Object o2) {  
            	Edge one = (Edge) o1;    
            	Edge two = (Edge) o2;
                if(one.low < two.low)
                	return -1;
                else if(one.low > two.low)
                	return 1;
                else{
                	return one.high < one.high? -1: 1;
                }
            }    
        });

		int[][] edge = new int[2][set.size()];
		i = 0;
		for (Edge e : undirectEdge) {
			edge[0][i] = e.low;
			edge[1][i] = e.high;
			i++;
		}

		Clustering cluster = ModularityOptimizer.cluster(nodeNum, edge);
		graph.setnCluster(cluster.getNClusters());
		i = 0;
		int[] cl = cluster.getClusters();
		System.out.println(cl.length);
		System.out.println(Arrays.toString(cl));;
		for (BasicWeiboUserNode nd : graph.getNodes()){
			System.out.print(i+", ");
			nd.setCluster(cl[i++]);
		}
			
	}

	private class Edge {
		int low;
		int high;

		Edge(int low, int high) {
			this.low = low;
			this.high = high;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + low;
			result = prime * result + high;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			Edge other = (Edge) obj;
			if (low != other.low)
				return false;
			if (high != other.high)
				return false;
			return true;
		}
	}
}
