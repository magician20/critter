// package com.magician.critter.config;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Primary;

// import com.zaxxer.hikari.HikariDataSource;

// //Return a dedicated implementation rather than DataSource

// public class DataSourceConfig {

//     @Bean
//     @ConfigurationProperties(prefix = "spring.critter.datasource")
//     public HikariDataSource dataSource() {
//         return DataSourceBuilder.create().type(HikariDataSource.class).build();
//     }
    
// }
