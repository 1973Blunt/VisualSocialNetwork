package com.relation.neo4j.sdn.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.relation.neo4j.sdn.entity.WeiboUser;
import com.relation.neo4j.sdn.repository.WeiboUserRestRepository;
import com.relation.neo4j.sdn.result.*;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月5日
 */
@Service
@Transactional
public class WeiboUserRestService {
	@Autowired
	WeiboUserRestRepository weiboUserRepository;

	private BasicWeiboGraph toD3Format(List<Map<String, Object>> result) {
		BasicWeiboGraph graph = new BasicWeiboGraph();
		Set<Long> idSet = new HashSet<Long>();

		for (Map<String, Object> row : result) {
			Map<String, Object> map = (Map<String, Object>) row.get("tail");
			BasicWeiboUserNode tail = new BasicWeiboUserNode();
			tail.setId(convert2Long(map.get("tid")));
			tail.setNickName((String) map.get("tname"));
			if (!idSet.contains(tail.getId())) {
				idSet.add(tail.getId());
				graph.addNode(tail);
			}
		}

		int i=0;
		for (Map<String, Object> row : result) {
			BasicWeiboUserNode tail = graph.getNodes().get(i++);
			for(Object obj : (Object[]) row.get("heads")){
				Map<String, Object> map = (Map<String, Object>) obj;
				long hid = convert2Long(map.get("hid"));
				if(!idSet.contains(hid)){
					BasicWeiboUserNode head = new BasicWeiboUserNode();
					head.setNickName((String) map.get("hname"));
					head.setId(hid);
					idSet.add(hid);
					graph.addNode(head);
				}
				graph.addEdge(new BasicFollowsEdge(hid, tail.getId()));
			}
		}
		
		return graph;
	}

	private Long convert2Long(Object obj) {
		if (obj.getClass() == Long.class)
			return (Long) obj;
		else
			return ((Integer) obj).longValue();
	}

	@Transactional
	public BasicWeiboGraph graph(int limit) {
		List<Map<String, Object>> result = weiboUserRepository.graph(limit);
		return toD3Format(result);
	}
	
	public BasicWeiboGraph getPath(String sNickName, String tNickName){
		Iterable<Map<String, Object>> result = weiboUserRepository.findShortestPath(sNickName, tNickName);
		BasicWeiboGraph graph = new BasicWeiboGraph();
		for (Map<String, Object> row : result) {
//			List<Map<String, Object>> path = (List<Map<String, Object>>)row.get("path");
			// TODO	查找最短路径目前由WeiboUserJDBCService实现
			Object obj = row.get("path");
			System.out.println(obj);
			boolean is = obj instanceof Object[];
			System.out.println(is);
			is = obj instanceof Map;
			System.out.println(is);
			is = obj instanceof List;
			System.out.println(is);
		}
		return graph;
	}

	@Transactional
	public BasicWeiboGraph getRelatedWeiboUser(String nickname) {
		List<Map<String, Object>> result = weiboUserRepository.getRelatedWeiboUser(nickname);
		BasicWeiboGraph graph = new BasicWeiboGraph();
		Set<Long> idSet = new HashSet<Long>();
		for (Map<String, Object> row : result) {
			WeiboUser head = (WeiboUser) row.get("fan");
			WeiboUser tail = (WeiboUser) row.get("user");
			if (!idSet.contains(head.getWb_usr_id())) {
				graph.addNode(new BasicWeiboUserNode(head));
				idSet.add(head.getWb_usr_id());
			}
			if (!idSet.contains(tail.getWb_usr_id())) {
				graph.addNode(new BasicWeiboUserNode(tail));
				idSet.add(tail.getWb_usr_id());
			}

			BasicFollowsEdge edge = new BasicFollowsEdge();
			edge.setSource(head.getWb_usr_id());
			edge.setTarget(tail.getWb_usr_id());
			graph.addEdge(edge);
		}
		return graph;
	}
}
