//package com.eun.common.db;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
//@Configuration
//@MapperScan(basePackages = {"com.eun"})
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
//    @Autowired ApplicationContext applicationContext;
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(DB_DRIVER);
//        dataSource.setUrl(DB_URL);
//        dataSource.setUsername(DB_USERNAME);
//        dataSource.setPassword(DB_PASSWORD);
//
//        return dataSource;
//    }
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
//        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*Mapper.xml"));
//        return factoryBean;
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}
