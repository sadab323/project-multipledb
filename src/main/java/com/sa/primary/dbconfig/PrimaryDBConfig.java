package com.sa.primary.dbconfig;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.sa.primary.repo", entityManagerFactoryRef = "primaryEntityManager", transactionManagerRef = "primaryTransactionManager")
public class PrimaryDBConfig {

	@Autowired
	Environment env;
	
	@Bean("primaryEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(primaryDataSource());
		em.setPackagesToScan(new String[] {"com.sa.primary.model"});
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		map.put("hibernate.dialect", env.getProperty("hibernate.primary.dialect"));
		em.setJpaPropertyMap(map);
		return em;
	}

	@Primary
	@Bean
	public DataSource primaryDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.primary.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.primary.url"));
		dataSource.setUsername(env.getProperty("spring.primary.username"));
		dataSource.setPassword(env.getProperty("spring.primary.password"));
		return dataSource;
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager primaryTransactionManager() {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(primaryEntityManager().getObject());
		return transactionManager;
	}
}
