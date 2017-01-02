package com.relation.neo4j;

import com.relation.neo4j.sdn.entity.WeiboUser;
import com.relation.neo4j.sdn.result.BasicFollowsEdge;
import com.relation.neo4j.sdn.result.BasicWeiboGraph;
import com.relation.neo4j.sdn.result.BasicWeiboUserNode;
import com.relation.neo4j.sdn.service.WeiboUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dxx on 2016/10/11.
 */
@SpringBootApplication
@RestController
public class Application extends SpringBootServletInitializer {
	public static final int DEFAULT_NODE_LIMIT = 100;
	@Autowired
	WeiboUserService weiboUserService;
	
	@RequestMapping("/test")
	public String greeting() {
		return "helloworld";
	}

	@RequestMapping("/getGraph")
	public BasicWeiboGraph getGraph() {
		ArrayList<WeiboUser> followedUsers = (ArrayList<WeiboUser>) weiboUserService.getGraph(DEFAULT_NODE_LIMIT);
		System.out.println("get getGraph " + followedUsers.size());
		return toGraph(followedUsers);
	}

	@RequestMapping(value = "/getFollowedUsers", method = RequestMethod.POST)
	public BasicWeiboGraph getFollowedUsers(String nickName) {
		ArrayList<WeiboUser> followedUsers = (ArrayList<WeiboUser>) weiboUserService.getFollowedUsers(nickName);
		System.out.println("get followedUsers " + followedUsers.size());
		return toGraph(followedUsers);
	}
	
	@RequestMapping(value = "/getFansAndFollow", method = RequestMethod.POST)
	public BasicWeiboGraph getFansAndFollow(String nickName) {
		ArrayList<WeiboUser> followedUsers = (ArrayList<WeiboUser>) weiboUserService.getFansAndFollow(nickName);
		System.out.println("get getFansAndFollow " + followedUsers.size());
		return toGraph(followedUsers);
	}
	
	public BasicWeiboGraph toGraph(ArrayList<WeiboUser> users){
		BasicWeiboGraph graph = new BasicWeiboGraph();
		if (users != null && users.size() > 0) {
			graph.addNodes(BasicWeiboUserNode.getListByWeiboUser(users));
			
			Set<Long> userIdSet = new HashSet<Long>();
			for(WeiboUser usr : users)
				userIdSet.add(usr.getWb_usr_id());
			
			for (WeiboUser user : users)
				graph.addEdges(BasicFollowsEdge.getListByOutgoing(user, userIdSet));
			System.out.println(graph.getEdges().size());
		}
		return graph;
	}

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public WeiboUser getUserInfo(String nickName) {
		WeiboUser weiboUser = weiboUserService.getUserInfo(nickName);
		return weiboUser;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
