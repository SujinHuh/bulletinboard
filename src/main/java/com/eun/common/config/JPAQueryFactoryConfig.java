//package com.eun.config;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Configuration
//public class JPAQueryFactoryConfiguration {
//
//    /**
//     * EntityManagerFactory 를 통해 생성되는 EntityManager
//     * Entity 를 저장, 수정, 삭제, 조회등 엔티티와 관련된 작업을 수행
//     */
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    /**
//     * EntityManager 를 이용하여 쿼리를 코드로 작성할 수 있도록 도와주는 객체
//     */
//    @Bean
//    public JPAQueryFactory jpaQueryFactory() {
//        return new JPAQueryFactory(entityManager);
//    }
//}
