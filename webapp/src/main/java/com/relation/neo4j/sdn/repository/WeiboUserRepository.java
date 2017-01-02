package com.relation.neo4j.sdn.repository;

import com.relation.neo4j.sdn.entity.WeiboUser;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dxx on 2016/10/14.
 */
@Repository
public interface WeiboUserRepository extends GraphRepository<WeiboUser> {
    @Query("MATCH (user:WeiboUser) where user.NickName={nickName} RETURN user")
    WeiboUser getUserByName(@Param("nickName") String nickName);

    @Query("MATCH p=(user:WeiboUser)<-[FOLLOWS]-(fan:WeiboUser) where user.NickName={nickName} RETURN p")
    List<WeiboUser> getFansInfo(@Param("nickName") String nickName);
    
    @Query("MATCH p=(user:WeiboUser)-[FOLLOWS]-(fan:WeiboUser) where user.NickName={nickName} RETURN p")
    List<WeiboUser> getFansAndFollow(@Param("nickName") String nickName);
    
    @Query("MATCH p=(user:WeiboUser)<-[FOLLOWS]-(fan:WeiboUser) RETURN p limit {limit}")
    List<WeiboUser> getGraph(@Param("limit") Integer limit);
}
