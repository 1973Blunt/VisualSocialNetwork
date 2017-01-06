package com.relation.neo4j.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dxx on 2016/10/11.
 */
@Configuration
// 启动类的@SpringBootApplication会自动扫描同级包以及子包，所以下面的@ComponentScan不加应该没关系
@ComponentScan("com.relation.neo4j")
@EnableNeo4jRepositories("com.relation.neo4j.sdn.repository")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {
	public static final String SERVER_ADDR = "119.29.174.202";
	public static final String USER = "neo4j";
	public static final String PASSWD = "0202";

	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
		org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
		config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
				.setURI("http://" + USER + ":" + PASSWD + "@" + SERVER_ADDR + ":7474/");
		return config;
	}

	@Override
	public SessionFactory getSessionFactory() {
		/**
		 * 如果不指定节点映射的java bean路径，保存时会报如下警告，导致无法将节点插入Neo4j中 ... is not an
		 * instance of a persistable class
		 */
		return new SessionFactory(getConfiguration(), "com.relation.neo4j.sdn.entity");
	}

	// @Override
	// @Bean
	// @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	// public Session getSession() throws Exception {
	// return super.getSession();
	// }

}
