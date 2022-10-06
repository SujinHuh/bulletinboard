//package com.eun.common.db;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class DatabaseConfig {
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String DB_DRIVER;
//    @Value("${spring.datasource.url}")
//    private String DB_URL;
//    @Value("${spring.datasource.username}")
//    private String DB_USERNAME;
//    @Value("${spring.datasource.password}")
//    private String DB_PASSWORD;
//
////
////
////    @Bean
////    public SqlSessionFactory sqlSessionFactory() throws Exception {
////        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
////        factoryBean.setDataSource(dataSource());
////        return factoryBean.getObject();
////    }
//}
