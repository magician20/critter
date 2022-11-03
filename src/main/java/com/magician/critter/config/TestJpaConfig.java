// package com.magician.critter.config;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import
// org.springframework.transaction.annotation.EnableTransactionManagement;

// import com.zaxxer.hikari.HikariDataSource;

// @Configuration
// @PropertySource("application-test.properties")
// @EnableJpaRepositories(basePackages = {
// "com.magician.critter.pet.data",
// "com.magician.critter.schedule.data",
// "com.magician.critter.user.data"
// })
// @EnableTransactionManagement
// public class TestJpaConfig {

// // @Bean
// @Profile("test")
// // // @ConfigurationProperties(prefix = "spring.datasource")
// // public HikariDataSource testDataSource() {
// // return DataSourceBuilder.create().type(HikariDataSource.class).build();
// // }

// }
