package com.relation.neo4j;

import com.relation.neo4j.sdn.result.BasicWeiboGraph;
import com.relation.neo4j.sdn.service.ClusterService;
import com.relation.neo4j.sdn.service.WeiboUserJDBCService;
import com.relation.neo4j.sdn.service.WeiboUserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dxx on 2016/10/11.
 */
@SpringBootApplication
@RestController
public class Application extends SpringBootServletInitializer {
	public static final int DEFAULT_NODE_LIMIT = 200;

	@Autowired
	WeiboUserRestService weiboUserRestService;

	@Autowired
	ClusterService clusterService;

	@Autowired
	WeiboUserJDBCService jdbcService;

	@RequestMapping("/test")
	public String greeting() {
		return "Hello Neo4j!";
	}

	@RequestMapping("/getGroup")
	public BasicWeiboGraph getGroup(
			@RequestParam(value = "nodesNum", defaultValue = "" + DEFAULT_NODE_LIMIT) Integer nodesNum) {
		BasicWeiboGraph graph = weiboUserRestService.graph(nodesNum);
		System.out.println("get node num: " + graph.getNodes().size());
		System.out.println("get edge num: " + graph.getEdges().size());
		clusterService.cluster(graph);
		return graph;
	}

	@RequestMapping(value = "/getPath", method = RequestMethod.POST)
	public BasicWeiboGraph getPath(String sourceNickName, String targetNickName) {
		BasicWeiboGraph graph = jdbcService.findShortestPaths(sourceNickName, targetNickName);
		System.out.println("get node num: " + graph.getNodes().size());
		System.out.println("get edge num: " + graph.getEdges().size());
		return graph;
	}

	@RequestMapping("/getGraph")
	public BasicWeiboGraph getGraph() {
		BasicWeiboGraph graph = weiboUserRestService.graph(DEFAULT_NODE_LIMIT);
		System.out.println("get getGraph " + graph.getNodes().size());
		return graph;
	}

	@RequestMapping(value = "/getFansAndFollow", method = RequestMethod.POST)
	public BasicWeiboGraph getFansAndFollow(String nickName) {
		BasicWeiboGraph graph = weiboUserRestService.getRelatedWeiboUser(nickName);
		System.out.println("get getFansAndFollow " + graph.getNodes().size());
		return graph;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
