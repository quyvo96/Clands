package edu.vinaenter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("edu.vinaenter.*")
@PropertySource("classpath:jdbc/database.properties") //đọc file properties trong thư mục jdbc, classpath ngang vs réuorce 
public class JdbcConfig {
	@Autowired
	Environment environment;  //hổ trợ đọc file properties
	private final String URL = "url";
	private final String USER = "user";
	private final String DRIVER = "driver";
	private final String PASSWORD = "pass";

	@Bean
	public JdbcTemplate jdbcTemplate() {   //jdbctemple có sẵn các phương thức sql: update, select...
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}
}
