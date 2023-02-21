//package com.sa.secondary.dbconfig;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableJpaRepositories(
//		entityManagerFactoryRef = "secondaryEntityManagerFactory",
//		transactionManagerRef = "secondaryTransactionManager",
//		basePackages = {"com.sa.secondary.repo"}
//				)
//public class SecondaryDatabaseConnection {
//	
//	@Value("${spring.secondary.url}")
//	private String url;
//	
//	@Value("${spring.secondary.username}")
//	private String username;
//	
//	@Value("${spring.secondary.password}")
//	private String password;
//	
//	//@Primary
//	@Bean(name="secondaryDbDataSource")
//	public DataSource secondaryDbDataSource() {
//		return DataSourceBuilder.create()
//				.url(url)
//				.username(username)
//				.password(password)
//				.build();
//		
//	}
//	
////	@Primary
//	@Bean(name="secondaryEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
//			EntityManagerFactoryBuilder builder,
//			@Qualifier("secondaryDbDataSource") DataSource secondaryDataSource) {
//		return builder
//				.dataSource(secondaryDataSource)
//				.packages("com.sa.secondary.model")
//				.build();
//	}
//	
//	//@Primary
//	@Bean(name="secondaryTransactionManager")
//	public PlatformTransactionManager secondaryTransactionManager(
//			@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
//		return new JpaTransactionManager(secondaryEntityManagerFactory);
//	}
//
//
//}
