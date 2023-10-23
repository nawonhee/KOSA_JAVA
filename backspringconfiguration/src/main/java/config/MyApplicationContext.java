package config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan (basePackages={"com.my.di.dto",
								"com.my.product.dao",
								"com.my.customer.dao",
								"com.my.order.dao",
								"com.my.product.service",
								"com.my.customer.service",
								"com.my.order.service"})
@EnableTransactionManagement
public class MyApplicationContext {
	
	@Bean
	public SimpleDriverDataSource dataSource() {
		SimpleDriverDataSource sdds = new SimpleDriverDataSource();
		sdds.setDriverClass(oracle.jdbc.OracleDriver.class);
		sdds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		sdds.setUsername("hr");
		sdds.setPassword("hr");
		return sdds;
	}
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("hr");
		config.setPassword("hr");
		config.setMinimumIdle(3);
		return config;
	}
	
	@Bean
	public DataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSourceHikari());
		Resource resource = new ClassPathResource("mybatis-config.xml");
		ssfb.setConfigLocation(resource);
		return (SqlSessionFactory)ssfb.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSourceHikari());
		return tx;
	}
}
