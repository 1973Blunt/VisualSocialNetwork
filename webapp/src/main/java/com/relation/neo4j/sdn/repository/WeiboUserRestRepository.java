package com.relation.neo4j.sdn.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.relation.neo4j.sdn.entity.WeiboUser;

/**
 * @author chuan email:summer15y@163.com
 * @version date: 2017年1月5日
 */
@RepositoryRestResource()
public interface WeiboUserRestRepository extends GraphRepository<WeiboUser> {

	@Query("MATCH (t:WeiboUser)-[:FOLLOWS]->(h:WeiboUser) " + "RETURN {tid:t.wb_usr_id, tname:t.NickName} as tail, "
			+ "collect({hid:h.wb_usr_id, hname:h.NickName}) as heads LIMIT {limit}")
	List<Map<String, Object>> graph(@Param("limit") int limit);

	@Query("MATCH (user:WeiboUser)<-[:FOLLOWS]-(fan:WeiboUser)  where user.NickName={nickName} or fan.NickName={nickName} RETURN user, fan")
	List<Map<String, Object>> getRelatedWeiboUser(@Param("nickName") String nickname);

	@Query("MATCH (s {NickName: {sNickName}}), (t{NickName: {tNickName}}) MATCH path=shortestPath((s)-[*]-(t)) RETURN path")
	Iterable<Map<String, Object>> findShortestPath(@Param("sNickName") String sNickName, @Param("tNickName") String tNickName);
}
