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
@EnableJpaRepositories(basePackages = "com.sa.secondary.repo", entityManagerFactoryRef = "secondaryEntityManager", transactionManagerRef = "secondaryTransactionManager")
public class SecondaryDBConfig {

	@Autowired
	Environment env;
	
	@Bean("secondaryEntityManager")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(secondaryDataSource());
		em.setPackagesToScan(new String[] {"com.sa.secondary.model"});
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		map.put("hibernate.dialect", env.getProperty("hibernate.secondary.dialect"));
		em.setJpaPropertyMap(map);
		return em;
	}

	@Bean
	public DataSource secondaryDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.secondary.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.secondary.url"));
		dataSource.setUsername(env.getProperty("spring.secondary.username"));
		dataSource.setPassword(env.getProperty("spring.secondary.password"));
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager secondaryTransactionManager() {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(secondaryEntityManager().getObject());
		return transactionManager;
	}
}
