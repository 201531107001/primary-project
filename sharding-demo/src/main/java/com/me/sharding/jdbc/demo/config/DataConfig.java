package com.me.sharding.jdbc.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.me.sharding.jdbc.demo.sharding.DemoTableShardingAlgorithm;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataConfig {

	/**
	 * 配置分库分表策略
	 *
	 * @return
	 * @throws SQLException
	 */
	@Bean(name = "shardingDataSource")
	DataSource getShardingDataSource() throws SQLException {
		// 设置分库映射
		Map<String, DataSource> dataSourceMap = new HashMap<>(1);
		// 添加数据库到map里
		dataSourceMap.put("test", getDateSource());

		// 设置默认db为test，也就是为那些没有配置分库分表策略的指定的默认库
		// 如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
		DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "test");

		// 设置分表映射，将tb_student_0和tb_student_1两个实际的表映射到tb_student逻辑表
		// 0和1两个表是真实的表，tb_student是个虚拟不存在的表，只是供使用。如查询所有数据就是select * from tb_student 就能查完0和1表的
		TableRule orderTableRule = TableRule.builder("tb_student")
				.actualTables(Arrays.asList("tb_student_0", "tb_student_1"))
				.dataSourceRule(dataSourceRule)
				.build();

		// 具体分库分表策略，按什么规则来分
		ShardingRule shardingRule = ShardingRule.builder()
				.dataSourceRule(dataSourceRule)
				.tableRules(Arrays.asList(orderTableRule))
				.tableShardingStrategy(new TableShardingStrategy("age", new DemoTableShardingAlgorithm())).build();

		DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);

		return dataSource;
	}


	@Bean
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("shardingDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath*:com.me.sharding.jdbc.demo.mapper/*.xml"));
		return bean.getObject();
	}

	@Bean
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean("dataSource")
	public DataSource getDateSource() {
		DruidDataSource result = new DruidDataSource();
		// result.setDriverClassName("com.mysql.cj.jdbc.Driver");
		result.setUrl("jdbc:log4jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf-8");
		result.setDriverClassName("net.sf.log4jdbc.DriverSpy");
		result.setUsername("root");
		result.setPassword("990219");
		return result;
	}
}
