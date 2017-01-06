package com.relation.neo4j.sdn.service;

import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.v1.types.Path;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.relation.neo4j.config.Neo4jConfig;
import com.relation.neo4j.sdn.result.BasicFollowsEdge;
import com.relation.neo4j.sdn.result.BasicWeiboGraph;
import com.relation.neo4j.sdn.result.BasicWeiboUserNode;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月5日
 */
@Service
@Transactional
public class WeiboUserJDBCService {
	public BasicWeiboGraph findShortestPaths(String n1Nickname, String n2Nickname) {
		BasicWeiboGraph graph = new BasicWeiboGraph();
		// Connect
		Driver driver = GraphDatabase.driver("bolt://" + Neo4jConfig.SERVER_ADDR + ":7687",
				AuthTokens.basic(Neo4jConfig.USER, Neo4jConfig.PASSWD));
		Session session = driver.session();

		StatementResult result = session.run(
				"MATCH (s {NickName: {sNickName}}), (t{NickName: {tNickName}}) MATCH path=shortestPath((s)-[*]-(t)) RETURN path",
				parameters("sNickName", n1Nickname, "tNickName", n2Nickname));
		Set<Long> idSet = new HashSet<Long>();
		while (result.hasNext()) {
			Record record = result.next();
			Path path = ((PathValue) record.get("path")).asPath();
			for (Path.Segment seg : path) {
				Map<String, Object> startMap = seg.start().asMap();
				Long idStart = convert2Long(startMap.get("wb_usr_id"));
				if (!idSet.contains(idStart)) {
					BasicWeiboUserNode start = new BasicWeiboUserNode();
					start.setId(idStart);
					start.setNickName((String) startMap.get("NickName"));
					graph.addNode(start);
				}

				Map<String, Object> endMap = seg.end().asMap();
				Long idEnd = convert2Long(endMap.get("wb_usr_id"));
				if (!idSet.contains(idEnd)) {
					BasicWeiboUserNode end = new BasicWeiboUserNode();
					end.setId(idEnd);
					end.setNickName((String) endMap.get("NickName"));
					graph.addNode(end);
				}

				graph.addEdge(new BasicFollowsEdge(idStart, idEnd));
			}
		}

		session.close();
		driver.close();
		return graph;
	}

	private Long convert2Long(Object obj) {
		if (obj.getClass() == Long.class)
			return (Long) obj;
		else
			return ((Integer) obj).longValue();
	}
}
