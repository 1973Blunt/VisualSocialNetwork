package com.relation.neo4j.sdn.repository;

import com.relation.neo4j.sdn.entity.WeiboUser;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dxx on 2016/10/14.
 */
@Repository
public interface WeiboUserRepository extends GraphRepository<WeiboUser> {

}
